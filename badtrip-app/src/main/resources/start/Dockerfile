FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} badtrip-app-0.0.1-SNAPSHOT.jar.jar
ENTRYPOINT ["java", "-jar", "/badtrip-app-0.0.1-SNAPSHOT.jar"]