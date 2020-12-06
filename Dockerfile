#maven build - Stage 1
FROM maven:3.6.3-jdk-8-slim as MAVEN_3_6_3_BUILD

RUN mvn clean install


# Stage 2 creating a java image based on the output from maven image.
FROM openjdk:8-alpine


COPY --from=MAVEN_3_6_3_BUILD target/user-mgmt-service.jar /user-mgmt-service.jar

EXPOSE 8080

CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/user-mgmt-service.jar"]