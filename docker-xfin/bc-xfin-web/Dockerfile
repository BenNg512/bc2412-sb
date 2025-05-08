FROM openjdk:17
LABEL maintainer="benng512@gmail.com"
ADD target/xfin-web-0.0.1-SNAPSHOT.jar xfin-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","xfin-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8092