FROM tomcat:9.0.45
RUN rm -rf /usr/local/tomcat/webapps/*
COPY build/libs/droid-0.0.1-SNAPSHOT.jar /usr/local/tomcat/webapps/ROOT.war
EXPOSE 4001
CMD ["catalina.sh", "run"]