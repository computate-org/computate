#!/bin/bash
source "$(dirname $0)/00-functions.sh"

#############
# zookeeper #
#############

if hash apt-get 2>/dev/null ; then
	computate "sudo apt-get install -y ivy lsof ant autoconf automake libcppunit-dev"
fi
if hash yum 2>/dev/null ; then
	computate "sudo yum install -y ivy lsof ant autoreconf automake cppunit-devel"
fi
computate "sudo mkdir /usr/local/src/zookeeper"
computate "sudo chown $USER: /usr/local/src/zookeeper"

computate "git clone https://github.com/apache/zookeeper.git /usr/local/src/zookeeper"
computate "cd /usr/local/src/zookeeper; git checkout release-$versionZookeeper"
computate "cd /usr/local/src/zookeeper/zookeeper; ant package"
computate "sudo cp -r /usr/local/src/zookeeper/build/zookeeper-$versionZookeeper-beta /srv/zookeeper-$versionZookeeper"
computate "sudo chown -R $USER: /srv/zookeeper-$versionZookeeper"

computate "echo '
tickTime=2000
dataDir=/srv/zookeeper-$versionZookeeper/data
clientPort=$portClientZookeeper
admin.serverPort=$portAdminZookeeper
' | tee /srv/zookeeper-$versionZookeeper/conf/zoo.cfg"

computate "echo '
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
' | sudo tee /usr/lib/systemd/system/zookeeper-$versionZookeeper.service"
computate "sudo systemctl daemon-reload"
computate "sudo systemctl restart zookeeper-$versionZookeeper.service"
computate "systemctl status zookeeper-$versionZookeeper.service --no-pager"
computate "sudo systemctl enable zookeeper-$versionZookeeper.service"
