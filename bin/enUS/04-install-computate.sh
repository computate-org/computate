#!/bin/bash
export appComputatePath="${appComputatePath:-/usr/local/src/computate}"
source "$appComputatePath/bin/enUS/00-functions.sh"

#############
# computate #
#############

computate "cd $appComputatePath && mvn install"

computate "echo '
[Unit]
Description=Watch the computate app and generate Java code when the Java source files are modified. 
After=network.target

[Service]
Type=simple
User=$USER
Group=$USER
WorkingDirectory=$appPath
Environment=appName=computate
Environment=appPath=$appComputatePath
Environment=appComputatePath=$appComputatePath
ExecStart=$appComputatePath/bin/$languageName/watch.sh

[Install]
WantedBy=multi-user.target
' | sudo tee /usr/lib/systemd/system/watch-computate.service"
computate "sudo systemctl daemon-reload"
computate "sudo systemctl restart watch-computate.service"
computate "systemctl status watch-computate.service --no-pager"
computate "sudo systemctl enable watch-computate.service"
