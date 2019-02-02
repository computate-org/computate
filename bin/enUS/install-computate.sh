#!/bin/bash

export appComputatePath="${appComputatePath:-/usr/local/src/computate}"
source "$appComputatePath/bin/enUS/00-functions.sh"

computate "$appComputatePath/bin/$languageName/01-install-maven.sh"
computate "$appComputatePath/bin/$languageName/02-install-zookeeper.sh"
computate "$appComputatePath/bin/$languageName/03-install-solr.sh"
computate "$appComputatePath/bin/$languageName/04-install-computate.sh"
