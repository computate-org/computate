#!/bin/bash
export appComputatePath="${appComputatePath:-/usr/local/src/computate}"
source "$appComputatePath/bin/enUS/00-functions.sh"

#############
# computate #
#############

computate "cd $appComputatePath && mvn install"
