FROM maven:3.8.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build  /app/target/spring-boot-docker.jar /app/spring-boot-docker.jar
ENTRYPOINT [ "java","-jar","spring-boot-docker.jar" ]