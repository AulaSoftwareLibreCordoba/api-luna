FROM openjdk:21-jdk-slim

# Instalar Maven manualmente
RUN apt-get update \
    && apt-get install -y maven \
    && apt-get clean

WORKDIR /app

COPY pom.xml /app/
RUN mvn dependency:go-offline

COPY src /app/src

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]
