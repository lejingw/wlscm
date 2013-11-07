@echo off
#ant -buildfile build222.xml -l build_log.log

plink -ssh -pw spkc000000 spkc@192.168.0.222 -m shutdown.sh

pscp -p -pw spkc000000 -r dist\wlscm.war spkc@192.168.0.222:/mclon/spkc/tomcat/webapps

plink -ssh -pw spkc000000 spkc@192.168.0.222 -m startup.sh
