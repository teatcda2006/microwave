FROM maven:3.9.8-eclipse-temurin-21 AS builder
WORKDIR /app

COPY . /app/.


RUN --mount=type=cache,target=/root/.m2 mvn clean package  -Dmaven.test.skip


FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/*.jar"]