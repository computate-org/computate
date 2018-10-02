#!/bin/bash
source "$(dirname $0)/00-functions.sh"

#############
# computate #
#############

computate "cd $appPath && mvn install"

computate "echo '
[Unit]
Description=Watch the computate app and generate Java code when the Java source files are modified. 
After=network.target

[Service]
Type=simple
User=$USER
Group=$USER
WorkingDirectory=$appPath
Environment=appName=$appName
Environment=appPath=$appliChemin
Environment=appComputatePath=$appComputatePath
ExecStart=$appComputatePath/bin/$languageName/watch.sh

[Install]
WantedBy=multi-user.target
' | sudo tee /usr/lib/systemd/system/watch-$appName.service"
computate "sudo systemctl daemon-reload"
computate "sudo systemctl restart watch-$appName.service"
computate "systemctl status watch-$appName.service --no-pager"
computate "sudo systemctl enable watch-$appName.service"
