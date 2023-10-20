FROM maven:3.8.1-adoptopenjdk-11 as builder

WORKDIR /app

COPY pom.xml .

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src src

RUN mvn package

FROM openjdk:11-jre-slim

COPY --from=builder /app/target/ /app/

EXPOSE 8080

ENTRYPOINT [ "java", "-jar" ,"/app/your-microservice.jar"]