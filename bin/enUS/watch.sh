#!/bin/bash
cd $COMPUTATE_SRC
mvn dependency:build-classpath -Dmdep.outputFile=$COMPUTATE_SRC/config/cp.txt -q
java -cp "$(cat $COMPUTATE_SRC/config/cp.txt):$COMPUTATE_SRC/target/classes" org.computate.frFR.java.RegarderRepertoire
