#!/bin/bash
CHEMIN_APPLI="$(readlink -f $( dirname $( dirname $( dirname ${BASH_SOURCE[0]} ) ) ) )"

echo "Watch the computate project here: $CHEMIN_APPLI"
mvn -q exec:java -Dexec.mainClass=org.computate.tout.java.RegarderRepertoire -Dexec.args="$CHEMIN_APPLI"
