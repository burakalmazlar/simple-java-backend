FROM openjdk:17-slim

ARG REVISION

EXPOSE 8080

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY target/simple-java-backend-${REVISION}.jar app.jar

CMD ["java","-jar","app.jar"]
