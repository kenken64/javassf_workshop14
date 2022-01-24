FROM openjdk:17-jdk-alpine
RUN addgroup -S springG && adduser -S spring -G springG
USER spring:springG
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} workshop14-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-jar", "/workshop14-0.0.1-SNAPSHOT.jar"]