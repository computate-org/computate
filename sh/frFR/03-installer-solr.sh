#!/bin/bash
source "$(dirname $0)/00-functions.sh"

########
# solr #
########

if hash apt-get 2>/dev/null ; then
	computate "sudo apt-get install -y ivy lsof ant autoconf automake libcppunit-dev"
fi
if hash yum 2>/dev/null ; then
	computate "sudo yum install -y ivy lsof ant autoreconf automake cppunit-devel"
fi
computate "sudo mkdir /usr/local/src/solr"
computate "sudo chown $USER: /usr/local/src/solr"

computate "git clone https://github.com/apache/lucene-solr.git /usr/local/src/solr"
computate "cd /usr/local/src/solr; git checkout releases/lucene-solr/$VERSION_SOLR"
computate "cd /usr/local/src/solr/solr; ant ivy-bootstrap"
computate "cd /usr/local/src/solr/solr; ant package"
computate "sudo cp -r /usr/local/src/solr/solr/build/solr-$VERSION_SOLR-SNAPSHOT /srv/solr-$VERSION_SOLR"
computate "sudo chown -R $USER: /srv/solr-$VERSION_SOLR"
computate "chmod +x /srv/solr-$VERSION_SOLR/bin/*"
computate "rm -rf /srv/solr-$VERSION_SOLR/example"
computate "cp /srv/solr-$VERSION_SOLR/server/solr/solr.xml /srv/solr-$VERSION_SOLR/"
computate "cat /srv/solr-$VERSION_SOLR/server/solr/solr.xml | perl -0777pe 's/(<str\s+name\s*=\s*\"host\"\s*>)\\\$\{host:\}(<\/str>)/\${1}localhost\${2}/gm' | tee /srv/solr-$VERSION_SOLR/server/solr/solr.xml"
computate "cat /srv/solr-$VERSION_SOLR/server/solr/solr.xml | perl -0777pe 's/(<int\s+name\s*=\s*\"hostPort\"\s*>)\\\$\{jetty.port:8983\}(<\/int>)/\${1}$PORT_SOLR\${2}/gm' | tee /srv/solr-$VERSION_SOLR/server/solr/solr.xml"

computate "echo '
[Unit]
Description=solr
After=zookeeper.service

[Service]
Type=forking
User=$USER
Group=$USER
WorkingDirectory=/srv/solr-$VERSION_SOLR
ExecStart=/srv/solr-$VERSION_SOLR/bin/solr -c -s /srv/solr-$VERSION_SOLR -h localhost -p $PORT_SOLR -z localhost:$PORT_CLIENT_ZOOKEEPER
LimitNOFILE=10000

[Install]
WantedBy=multi-user.target
' | sudo tee /usr/lib/systemd/system/solr-$VERSION_SOLR.service"
computate "sudo systemctl daemon-reload"
computate "sudo systemctl restart solr-$VERSION_SOLR.service"
computate "systemctl status solr-$VERSION_SOLR.service --no-pager"
computate "sudo systemctl enable solr-$VERSION_SOLR.service"

computate "ln -s $CHEMIN_APPLI/config/solr/server/solr/configsets/computate /srv/solr-$VERSION_SOLR/server/solr/configsets/computate"
computate "/srv/solr-$VERSION_SOLR/bin/solr zk upconfig -n computate -d /srv/solr-$VERSION_SOLR/server/solr/configsets/computate -z localhost:$PORT_CLIENT_ZOOKEEPER"
computate "/srv/solr-$VERSION_SOLR/bin/solr create_collection -c computate -n computate"

computate "xdg-open http://localhost:$PORT_SOLR"
