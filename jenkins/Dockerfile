FROM jenkins:2.32.1

COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/install-plugins.sh $(cat /usr/share/jenkins/plugins.txt)
COPY _seed.groovy /usr/share/jenkins/ref/init.groovy.d/_seed.groovy
