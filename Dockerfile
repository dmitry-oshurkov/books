FROM openjdk:11-jdk-alpine
COPY build/libs/books.war /home/dmitry/opt/apache-tomcat/deploy/books.war
CMD ["catalina.sh", "run"]
