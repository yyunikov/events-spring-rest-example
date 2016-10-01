# Events System
Typical event collecting system as example of using Spring Data Rest, Spring Boot, Spring HATEOAS, Gradle, MongoDb and Docker

How to run on Mac
=================================================================

Create Docker machine:
---------------------------------
  `docker-machine create --driver virtualbox default`

Run Mongo:
---------------------------------
  `docker run -p 27017:27017 -d --name mongodb mongo`

You can connect to MongoDb console using command:
---------------------------------
  `docker exec -it mongodb sh`

Gradle build (without tests):
---------------------------------
  `./gradlew build buildDocker -x test`

Run app:
---------------------------------
  `docker run -p 8080:8080 --link mongodb yyunikov/events-spring-rest-example`

TODO
---------------------------------
  docker-compose.yml file
