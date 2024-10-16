
FROM openjdk:17-jdk

COPY target/InternShipsManagement-0.0.1-SNAPSHOT.jar /app/InternShipsManagement.jar

WORKDIR /app
EXPOSE 8080

CMD ["java", "-jar", "InternShipsManagement.jar"]