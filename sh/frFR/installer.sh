#!/bin/bash

source "$(dirname $0)/00-functions.sh"

$appliChemin/sh/$langueNom/01-installer-maven.sh
$appliChemin/sh/$langueNom/02-installer-zookeeper.sh
$appliChemin/sh/$langueNom/03-installer-solr.sh
$appliChemin/sh/$langueNom/04-installer-computate.sh
