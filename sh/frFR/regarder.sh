#!/bin/bash
CHEMIN_appli="$(readlink -f $( dirname $( dirname $( dirname ${BASH_SOURCE[0]} ) ) ) )"

echo "Watch the computate project here: $CHEMIN_appli"
mvn exec:java -Dexec.mainClass=org.computate.tout.java.RegarderRepertoire -Dexec.args="$CHEMIN_appli"
