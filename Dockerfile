FROM maven:3.8.3-openjdk-17 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:17
COPY --from=build /usr/src/app/target/job-app.jar /usr/app/job-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/job-app.jar"]