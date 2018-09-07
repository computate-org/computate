#!/bin/bash

source "$(dirname $0)/00-functions.sh"

$appliChemin/bin/$langueNom/01-installer-maven.sh
$appliChemin/bin/$langueNom/02-installer-zookeeper.sh
$appliChemin/bin/$langueNom/03-installer-solr.sh
$appliChemin/bin/$langueNom/04-installer-computate.sh
