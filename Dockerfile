FROM gradle:7.6-jdk17 AS build

WORKDIR /bks
COPY . .

RUN gradle build


FROM openjdk:17

COPY --from=build /bks/build/libs/*.jar ./books.jar

ENTRYPOINT ["java", "-Xmx2048m", "-jar", "books.jar"]
EXPOSE 8080
