FROM openjdk:11
COPY build/libs/books-*.*.??????.jar books.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-Xmx2048m", "-jar", "/books.jar"]
EXPOSE 8080
