#!/bin/bash
CHEMIN_appli="$( dirname $( dirname $( dirname ${BASH_SOURCE[0]} ) ) )"

ln -s $CHEMIN_appli/config/solr/server/solr/configsets/computate /srv/solr-7.1.0/server/solr/configsets/computate
/srv/solr-7.1.0/bin/solr zk upconfig -n computate -d /srv/solr-7.1.0/server/solr/configsets/computate -z localhost:10281
/srv/solr-7.1.0/bin/solr create_collection -c computate -n computate
