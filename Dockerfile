FROM openjdk:17
ADD target/job-app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]