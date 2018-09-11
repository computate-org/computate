#!/bin/bash
cd $appliComputateChemin
mvn dependency:build-classpath -Dmdep.outputFile=$appliComputateChemin/config/cp.txt -q
java -cp "$(cat $appliComputateChemin/config/cp.txt):$appliComputateChemin/target/classes" org.computate.frFR.java.RegarderRepertoire
