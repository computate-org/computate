#!/bin/bash

source "$(dirname $0)/00-functions.sh"

$appPath/bin/$languageName/01-install-maven.sh
$appPath/bin/$languageName/02-install-zookeeper.sh
$appPath/bin/$languageName/03-install-solr.sh
$appPath/bin/$languageName/04-install-computate.sh
