FROM maven:3.8.5-openjdk-17 as builder
WORKDIR /app
COPY . .
RUN mvn dependency:resolve
RUN mvn clean package -DskipTests

FROM dvmarques/openjdk-17-jdk-alpine-with-timezone:latest
WORKDIR  /app
COPY --from=builder ./app/target/*.jar ./application.jar
EXPOSE 8080

ENV POSTGRES_HOST = localhost
RUN echo "The env var POSTGRES_HOST value is ${POSTGRES_HOST}"

ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=production", "application.jar" ]
