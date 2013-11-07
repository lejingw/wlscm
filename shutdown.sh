export CATALINA_HOME=/mclon/spkc/tomcat
export JAVA_HOME=/usr/java/jdk1.6.0_21
export PATH=$JAVA_HOME/bin:$PATH
cd /mclon/spkc/tomcat/bin/
./shutdown.sh 1>/dev/null 2>/dev/null
cd ..
./deploy.sh