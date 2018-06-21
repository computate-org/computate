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
computate "cd /usr/local/src/zookeeper; git checkout release-$VERSION_ZOOKEEPER"
computate "cd /usr/local/src/zookeeper/zookeeper; ant package"
computate "sudo cp -r /usr/local/src/zookeeper/build/zookeeper-$VERSION_ZOOKEEPER-beta /srv/zookeeper-$VERSION_ZOOKEEPER"
computate "sudo chown -R $USER: /srv/zookeeper-$VERSION_ZOOKEEPER"

computate "echo '
tickTime=2000
dataDir=/srv/zookeeper-$VERSION_ZOOKEEPER/data
clientPort=$PORT_CLIENT_ZOOKEEPER
admin.serverPort=$PORT_ADMIN_ZOOKEEPER
' | tee /srv/zookeeper-$VERSION_ZOOKEEPER/conf/zoo.cfg"

computate "echo '
[Unit]
Description=zookeeper
After=network.target

[Service]
Type=forking
User=$USER
Group=$USER
WorkingDirectory=/srv/zookeeper-$VERSION_ZOOKEEPER
ExecStart=/srv/zookeeper-$VERSION_ZOOKEEPER/bin/zkServer.sh start
Restart=on-failure

[Install]
WantedBy=multi-user.target
' | sudo tee /usr/lib/systemd/system/zookeeper-$VERSION_ZOOKEEPER.service"
computate "sudo systemctl daemon-reload"
computate "sudo systemctl restart zookeeper-$VERSION_ZOOKEEPER.service"
computate "systemctl status zookeeper-$VERSION_ZOOKEEPER.service --no-pager"
computate "sudo systemctl enable zookeeper-$VERSION_ZOOKEEPER.service"
