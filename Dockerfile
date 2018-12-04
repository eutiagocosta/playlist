FROM openjdk:8-jre-alpine
MAINTAINER br.com.playlist
RUN mkdir /app
COPY build/libs/app.jar /app
WORKDIR /app
ENTRYPOINT exec java -jar app.jar