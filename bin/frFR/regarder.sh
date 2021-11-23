#!/bin/bash
cd $APPLI_COMPUTATE_CHEMIN
mvn dependency:build-classpath -Dmdep.outputFile=$APPLI_COMPUTATE_CHEMIN/config/cp.txt -q
java -cp "$(cat $APPLI_COMPUTATE_CHEMIN/config/cp.txt):$APPLI_COMPUTATE_CHEMIN/target/classes" org.computate.frFR.java.RegarderRepertoire
