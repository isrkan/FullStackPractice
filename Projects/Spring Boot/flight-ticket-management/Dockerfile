# Use an official OpenJDK runtime as a parent image for this Dockerfile
FROM openjdk:17-jdk-slim

# Set the working directory within the Docker container. All subsequent commands will be executed in this directory
WORKDIR /flight-ticket-management

# Copy the Spring Boot JAR file from the local host (the directory where the Docker build command is executed) to the container's working directory
COPY target/flight-ticket-management-0.0.1-SNAPSHOT.jar /flight-ticket-management-0.0.1-SNAPSHOT.jar

# Define the default command to run when the container starts
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /flight-ticket-management-0.0.1-SNAPSHOT.jar"]