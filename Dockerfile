FROM openjdk:11
VOLUME /tmp
ADD target/oblig2-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
