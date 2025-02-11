# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/*.jar app.jar

# Copy New Relic agent files if monitoring is enabled
COPY newrelic/ newrelic/

# Run the application with New Relic agent
ENTRYPOINT ["java", "-javaagent:newrelic/newrelic.jar", "-jar", "app.jar"]
