FROM eclipse-temurin:17-jre-alpine

LABEL MAINTAINER="Umer Farooq <farooq.umer1999@gmail.com>"

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]