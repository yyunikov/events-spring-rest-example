FROM java:8
MAINTAINER yyunikov@gmail.com
EXPOSE 8080
VOLUME /tmp
ADD events-spring-rest-example-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb/micros","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]