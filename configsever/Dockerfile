# Step 1: Use an official Java runtime as base image
FROM openjdk:17-jdk-alpine

		# Step 2: Set working directory inside the container
WORKDIR /app

		# Step 3: Copy the JAR file into the container
COPY target/configms.jar configms.jar

		# Step 4: Expose the port the app runs on
EXPOSE 8080

		# Step 5: Run the JAR file
ENTRYPOINT ["java", "-jar", "configms.jar"]