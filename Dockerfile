FROM maven:3.9.6-eclipse-temurin-21-alpine AS BUILD_IMAGE
COPY . .
RUN mvn -f ./pom.xml clean install

FROM amazoncorretto:21
COPY --from=BUILD_IMAGE /target/*.jar .
EXPOSE 7070
CMD ["java","-jar","./target/ToDoAppDeel3-0.0.1-SNAPSHOT.jar"]