#!/bin/bash

source "$(dirname $0)/00-functions.sh"

$appliComputateChemin/bin/$langueNom/01-installer-maven.sh
$appliComputateChemin/bin/$langueNom/02-installer-zookeeper.sh
$appliComputateChemin/bin/$langueNom/03-installer-solr.sh
$appliComputateChemin/bin/$langueNom/04-installer-computate.sh
