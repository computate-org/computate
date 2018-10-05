#!/bin/bash
source "$(dirname $0)/00-functions.sh"

#############
# zookeeper #
#############

if ( \
	[ ! -f /usr/share/doc/apache-ivy-*/README ] \
	|| [ ! -f /usr/lib64/libcppunit.so ] \
	|| ! $( which lsof > /dev/null ) \
	|| ! $( which ant > /dev/null ) \
	|| ! $( which autoconf > /dev/null ) \
	|| ! $( which automake > /dev/null ) \
	) ; then
	if hash apt-get 2>/dev/null ; then
		computate "sudo apt-get install -y ivy lsof ant autoconf automake libcppunit-dev"
	fi
	if hash yum 2>/dev/null ; then
		computate "sudo yum install -y apache-ivy lsof ant autoreconf automake cppunit-devel"
	fi
fi
if [ ! -d /usr/local/src/zookeeper ]; then
	computate "sudo mkdir /usr/local/src/zookeeper"
fi
if [ "$(ls -ld /usr/local/src/zookeeper | awk '{print $3}')" != "$USER" ]; then
	computate "sudo chown -R $USER: /usr/local/src/zookeeper"
fi
if [ ! -f /usr/local/src/zookeeper/build.xml ]; then
	computate "git clone https://github.com/apache/zookeeper.git /usr/local/src/zookeeper"
fi
if [ "$(cd /usr/local/src/zookeeper && git status --branch | head -n1 | perl -0777pe 's/.*?(\S+)$/${1}/g')" != "release-$versionZookeeper" ]; then
	computate "cd /usr/local/src/zookeeper; git checkout release-$versionZookeeper; git checkout -b release-$versionZookeeper"
fi
if [ ! -d /usr/local/src/zookeeper/build/zookeeper-$versionZookeeper-beta ]; then
	computate "cd /usr/local/src/zookeeper; ant package"
fi
if [ ! -d /srv/zookeeper-$versionZookeeper ]; then
	computate "sudo cp -r /usr/local/src/zookeeper/build/zookeeper-$versionZookeeper-beta /srv/zookeeper-$versionZookeeper"
fi
if [ "$(ls -ld /srv/zookeeper-$versionZookeeper | awk '{print $3}')" != "$USER" ]; then
	computate "sudo chown -R $USER: /srv/zookeeper-$versionZookeeper"
fi

export zookeeperConfigTexte="
tickTime=2000
dataDir=/srv/zookeeper-$versionZookeeper/data
clientPort=$portClientZookeeper
admin.serverPort=$portAdminZookeeper
"
export cheminTemporaire=$(mktemp)
echo "$zookeeperConfigTexte" > $cheminTemporaire
if ! cmp -s /srv/zookeeper-$versionZookeeper/conf/zoo.cfg $cheminTemporaire; then
	computate "echo '$zookeeperConfigTexte' | tee /srv/zookeeper-$versionZookeeper/conf/zoo.cfg"
fi

export zookeeperServiceTexte="
[Unit]
Description=zookeeper
After=network.target

[Service]
Type=forking
User=$USER
Group=$USER
WorkingDirectory=/srv/zookeeper-$versionZookeeper
ExecStart=/srv/zookeeper-$versionZookeeper/bin/zkServer.sh start
Restart=on-failure

[Install]
WantedBy=multi-user.target
"

echo "$zookeeperServiceTexte" > $cheminTemporaire
if ! cmp -s /usr/lib/systemd/system/zookeeper-$versionZookeeper.service $cheminTemporaire; then
	computate "echo '$zookeeperServiceTexte' | sudo tee /usr/lib/systemd/system/zookeeper-$versionZookeeper.service"
fi
computate "sudo systemctl daemon-reload"
computate "sudo systemctl restart zookeeper-$versionZookeeper.service"
computate "systemctl status zookeeper-$versionZookeeper.service --no-pager"
computate "sudo systemctl enable zookeeper-$versionZookeeper.service"
