#FROM tomcat:9.0.45
#RUN rm -rf /usr/local/tomcat/webapps/*
#COPY build/libs/client-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
#EXPOSE 4000
#CMD ["catalina.sh", "run"]

FROM openjdk:16

COPY build/libs/client-0.0.1-SNAPSHOT.war /app.war

EXPOSE 4000

CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/app.war"]
