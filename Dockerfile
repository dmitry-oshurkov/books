FROM gradle:7.6-jdk17-alpine AS build

WORKDIR /bks
COPY . .

ENV BUILD_MODE=PROD

RUN gradle clean buildFatJar --no-daemon


FROM openjdk:17

COPY --from=build /bks/build/libs/*.jar ./books.jar

ENTRYPOINT ["java", "-Xmx2048m", "-jar", "books.jar", "-P:books.datasource.url=jdbc:postgresql://192.168.15.100:5712/books"]
EXPOSE 8080
