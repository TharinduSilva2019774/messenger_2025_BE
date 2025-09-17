FROM openjdk:24-jdk
ARG JAR_FILE=target/*.jar
COPY ./target/messenger_2025-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]