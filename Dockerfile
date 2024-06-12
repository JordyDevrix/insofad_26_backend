FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/webshop-0.0.1-SNAPSHOT.jar /app/webshop-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "webshop-0.0.1-SNAPSHOT.jar"]
