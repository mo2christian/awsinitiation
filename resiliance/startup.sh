Content-Type: multipart/mixed; boundary="//"
MIME-Version: 1.0

--//
Content-Type: text/cloud-config; charset="us-ascii"
MIME-Version: 1.0
Content-Transfer-Encoding: 7bit
Content-Disposition: attachment; filename="cloud-config.txt"

#cloud-config
cloud_final_modules:
- [scripts-user, always]

--//
Content-Type: text/x-shellscript; charset="us-ascii"
MIME-Version: 1.0
Content-Transfer-Encoding: 7bit
Content-Disposition: attachment; filename="userdata.txt"

#!/bin/bash
apt update -y
apt install -y --fix-missing openjdk-11-jre-headless
apt install -y awscli
 
aws s3 cp s3://fr.softeamgroup.formation-awsinitiation/app.jar app.jar
java -jar -Dserver.port=80 app.jar > /var/log/todolist.log &