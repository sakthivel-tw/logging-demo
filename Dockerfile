FROM azul/zulu-openjdk-alpine:11.0.5 as intermediateDocker
WORKDIR /demolog

COPY build/libs/demolog-0.0.1-SNAPSHOT.jar /demolog/build/libs/
EXPOSE 8080
CMD ["java","-jar","build/libs/demolog-0.0.1-SNAPSHOT.jar"]