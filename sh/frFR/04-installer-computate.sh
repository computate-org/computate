#!/bin/bash
source "$(dirname $0)/00-functions.sh"

#############
# computate #
#############

computate "mvn install"

computate "echo '
[Unit]
Description=Regarder l'"'"'"'"'"'"'appli computate et générer decode Java lorsque les fichiers source Java sont modifiés. 
After=network.target

[Service]
Type=simple
User=$USER
Group=$USER
WorkingDirectory=$CHEMIN_APPLI
ExecStart=$CHEMIN_APPLI/sh/$NOM_LANGUE/regarder.sh
Restart=on-failure

[Install]
WantedBy=multi-user.target
' | sudo tee /usr/lib/systemd/system/regarder-computate.service"
computate "sudo systemctl daemon-reload"
computate "sudo systemctl restart regarder-computate.service"
computate "systemctl status regarder-computate.service --no-pager"
computate "sudo systemctl enable regarder-computate.service"
