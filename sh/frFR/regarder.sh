#!/bin/bash
cheminAppli="$(readlink -f $( dirname $( dirname $( dirname ${BASH_SOURCE[0]} ) ) ) )"

echo "Watch the computate project here: $cheminAppli"
mvn -q exec:java -Dexec.mainClass=org.computate.tout.java.RegarderRepertoire -Dexec.args="$cheminAppli"
