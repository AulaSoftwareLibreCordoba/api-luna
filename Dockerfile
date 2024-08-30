FROM openjdk:21-jdk-slim as build

WORKDIR /app

RUN apt-get update \
    && apt-get install -y maven \
    && apt-get clean

COPY pom.xml /app/
COPY src /app/src


RUN mvn clean package -DskipTests


FROM openjdk:21-jdk-slim

WORKDIR /app


COPY --from=build /app/target/*.jar /app/app.jar


EXPOSE 8080


CMD ["java", "-jar", "/app/app.jar"]