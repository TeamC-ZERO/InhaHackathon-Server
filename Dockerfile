FROM openjdk:17-alpine
COPY build/libs/InhaHackathon-Server-0.0.1-SNAPSHOT app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
