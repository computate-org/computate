#!/bin/bash
source "$(dirname $0)/00-functions.sh"

#############
# computate #
#############

computate "cd $siteChemin && mvn install"
