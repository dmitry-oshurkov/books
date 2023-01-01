FROM gradle:7.5.1-jdk17-focal AS build

WORKDIR /bks
COPY . .

RUN gradle build


FROM openjdk:17
COPY --from=build /bks/build/libs/*.jar ./books.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-Xmx2048m", "-jar", "books.jar"]
EXPOSE 8080
