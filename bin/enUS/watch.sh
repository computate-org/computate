#!/bin/bash
cd $APP_COMPUTATE_PATH
mvn dependency:build-classpath -Dmdep.outputFile=$APP_COMPUTATE_PATH/config/cp.txt -q
java -cp "$(cat $APP_COMPUTATE_PATH/config/cp.txt):$APP_COMPUTATE_PATH/target/classes" org.computate.frFR.java.RegarderRepertoire
