#!/bin/bash

source "$(dirname $0)/00-functions.sh"

$CHEMIN_APPLI/sh/$NOM_LANGUE/01-installer-maven.sh
$CHEMIN_APPLI/sh/$NOM_LANGUE/02-installer-zookeeper.sh
$CHEMIN_APPLI/sh/$NOM_LANGUE/03-installer-solr.sh
