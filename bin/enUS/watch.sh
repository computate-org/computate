#!/bin/bash
cd $APP_COMPUTATE
mvn dependency:build-classpath -Dmdep.outputFile=$APP_COMPUTATE/config/cp.txt -q
java -cp "$(cat $APP_COMPUTATE/config/cp.txt):$APP_COMPUTATE/target/classes" org.computate.frFR.java.RegarderRepertoire
