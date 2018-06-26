#!/bin/bash

source "$(dirname $0)/00-functions.sh"

$cheminAppli/sh/$nomLangue/01-installer-maven.sh
$cheminAppli/sh/$nomLangue/02-installer-zookeeper.sh
$cheminAppli/sh/$nomLangue/03-installer-solr.sh
$cheminAppli/sh/$nomLangue/04-installer-computate.sh
