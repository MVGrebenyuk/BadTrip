FROM adoptopenjdk/openjdk11:alpine-jre
ADD badtrip-app/target/*.jar /badtrip-app-1-SNAPSHOT.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /badtrip-app-0.0.1-SNAPSHOT.jar
