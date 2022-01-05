FROM openjdk:17-slim

ARG REVISION

EXPOSE 8080

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY target/hello-world-${REVISION}.jar hello.jar

CMD ["java","-jar","hello.jar"]
