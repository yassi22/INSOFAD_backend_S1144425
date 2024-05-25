FROM maven:3.9.6-eclipse-temurin-21-alpine
COPY . .
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080
CMD ["java","-jar","./target/ToDoAppDeel3-0.0.1-SNAPSHOT.jar"]
