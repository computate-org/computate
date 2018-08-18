#!/bin/bash
echo "Watch the $appliNom project here: $appliChemin"
cd $appliComputateChemin && mvn -q exec:java -Dexec.mainClass=org.computate.frFR.java.RegarderRepertoire
