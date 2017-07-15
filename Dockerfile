# FROM airhacks/payara
#FROM jboss/wildfly
FROM openjdk:alpine
VOLUME /tmp
#COPY ./target/loadb.war ${DEPLOYMENT_DIR}
#ADD ./target/loadb.war /opt/jboss/wildfly/standalone/deployments/
ADD ./target/playconsul-1.0-SNAPSHOT.jar playconsul.jar
RUN sh -c 'touch playconsul.jar'
EXPOSE 8080 8443 9990
MAINTAINER Bernd Moeller <moeller-bernd@gmx.de>


# RUN /opt/jboss/wildfly/bin/add-user.sh admin ZR20010$%! --silent
ENTRYPOINT ["sh", "-c", "java -jar playconsul.jar"]
