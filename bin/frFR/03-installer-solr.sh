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
computate "cd /usr/local/src/solr; git checkout releases/lucene-solr/$versionSolr"
computate "cd /usr/local/src/solr/solr; ant ivy-bootstrap"
computate "cd /usr/local/src/solr/solr; ant package"
computate "sudo cp -r /usr/local/src/solr/solr/build/solr-$versionSolr-SNAPSHOT /srv/solr-$versionSolr"
computate "sudo chown -R $USER: /srv/solr-$versionSolr"
computate "chmod +x /srv/solr-$versionSolr/bin/*"
computate "rm -rf /srv/solr-$versionSolr/example"
computate "cat /srv/solr-$versionSolr/server/solr/solr.xml | perl -0777pe 's/(<str\s+name\s*=\s*\"host\"\s*>)\\\$\{host:\}(<\/str>)/\${1}localhost\${2}/gm' | tee /srv/solr-$versionSolr/server/solr/solr.xml"
computate "cat /srv/solr-$versionSolr/server/solr/solr.xml | perl -0777pe 's/(<int\s+name\s*=\s*\"hostPort\"\s*>)\\\$\{jetty.port:8983\}(<\/int>)/\${1}$portSolr\${2}/gm' | tee /srv/solr-$versionSolr/server/solr/solr.xml"
computate "cp /srv/solr-$versionSolr/server/solr/solr.xml /srv/solr-$versionSolr/"

computate "echo '
[Unit]
Description=solr
After=zookeeper.service

[Service]
Type=forking
User=$USER
Group=$USER
WorkingDirectory=/srv/solr-$versionSolr
ExecStart=/srv/solr-$versionSolr/bin/solr -c -s /srv/solr-$versionSolr -h localhost -p $portSolr -z localhost:$portClientZookeeper
LimitNOFILE=10000

[Install]
WantedBy=multi-user.target
' | sudo tee /usr/lib/systemd/system/solr-$versionSolr.service"
computate "sudo systemctl daemon-reload"
computate "sudo systemctl restart solr-$versionSolr.service"
computate "systemctl status solr-$versionSolr.service --no-pager"
computate "sudo systemctl enable solr-$versionSolr.service"

computate "ln -s $siteChemin/config/solr/server/solr/configsets/computate /srv/solr-$versionSolr/server/solr/configsets/computate"
computate "/srv/solr-$versionSolr/bin/solr zk upconfig -n computate -d /srv/solr-$versionSolr/server/solr/configsets/computate -z localhost:$portClientZookeeper"
computate "/srv/solr-$versionSolr/bin/solr create_collection -n computate -c computate"

computate "xdg-open http://localhost:$portSolr"
