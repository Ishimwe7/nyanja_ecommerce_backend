# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /nyanja_ecommerce_backend

# Copy the local project files to the container
COPY . .
# Grant execution permission to the mvnw script
RUN chmod +x ./mvnw
# Build the project
RUN ./mvnw clean package

# Expose the port that your application will run on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "target/nyanja_ecommerce_backend.jar"]
