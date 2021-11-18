FROM maven:3.8.3-jdk-11-slim as build
WORKDIR /todo-backend-jooby
COPY pom.xml pom.xml
COPY src src
COPY conf conf
COPY views views
RUN mvn package

FROM openjdk:11-jdk-slim
WORKDIR /todo-backend-jooby
COPY --from=build /todo-backend-jooby/target/todo-backend-jooby-*.jar app.jar
COPY conf conf
COPY views views
EXPOSE 8080
EXPOSE 5000
CMD java -Dserver.port=$PORT -jar app.jar
