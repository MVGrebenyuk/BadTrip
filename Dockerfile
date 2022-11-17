FROM eclipse-temurin:17-jdk-jammy
WORKDIR .
COPY badtrip-app-1.0-SNAPSHOT.jar .
CMD ["-java", "-jar", "badtrip-app-1.0-SNAPSHOT.jar"]