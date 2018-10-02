#!/bin/bash
cd $appComputatePath
mvn dependency:build-classpath -Dmdep.outputFile=$appComputatePath/config/cp.txt -q
java -cp "$(cat $appComputatePath/config/cp.txt):$appComputatePath/target/classes" org.computate.enUS.java.WatchDirectory
