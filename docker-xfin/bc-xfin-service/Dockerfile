FROM openjdk:17-slim

RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*

LABEL maintainer="benng512@gmail.com"

COPY target/bc-xfin-service-0.0.1-SNAPSHOT.jar bc-xfin-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "bc-xfin-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8091
