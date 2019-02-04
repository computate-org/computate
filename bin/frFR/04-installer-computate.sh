#!/bin/bash
source "$(dirname $0)/00-functions.sh"

#############
# computate #
#############

computate "cd $appliChemin && mvn install"
