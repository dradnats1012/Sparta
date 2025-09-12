FROM amazoncorretto:21

WORKDIR /app

COPY build/libs/sparta-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
