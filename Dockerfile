FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/awsdeploylearn-0.0.1-SNAPSHOT.jar awsdeploylearn.jar
EXPOSE 8080
CMD ["java","-jar","awsdeploylearn.jar"]