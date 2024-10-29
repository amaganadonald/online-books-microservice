FROM maven:3.9.9 AS build
COPY . .
RUN mvn clean package -DskipTests

# Second stage: create the runtime image
FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY --from=build /target/*.jar eureka-server.jar
ENTRYPOINT ["java", "-jar", "eureka-server.jar", "--spring.profiles.active=prod"]