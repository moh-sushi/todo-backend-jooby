FROM maven:3.6.1-jdk-8-slim as build
WORKDIR /tobo-backend-jooby
COPY pom.xml pom.xml
COPY src src
COPY conf conf
RUN mvn package

FROM openjdk:8-jdk-slim
WORKDIR /tobo-backend-jooby
COPY --from=build /tobo-backend-jooby/target/tobo-backend-jooby-0.1-SNAPSHOT.jar app.jar
COPY conf conf
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
