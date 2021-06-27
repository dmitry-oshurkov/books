FROM openjdk:15-alpine

LABEL maintainer=DM
WORKDIR /app

COPY build/docker/libs libs/
COPY build/docker/resources resources/
COPY build/docker/classes classes/

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-Xmx2048m", "-cp", "/app/resources:/app/classes:/app/libs/*", "name.oshurkov.books.BooksApplicationKt"]
EXPOSE 8080
