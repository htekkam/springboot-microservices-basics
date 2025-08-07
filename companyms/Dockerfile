# Step 1: Use an official Java runtime as base image
FROM openjdk:17-jdk-alpine

		# Step 2: Set working directory inside the container
WORKDIR /app

		# Step 3: Copy the JAR file into the container
COPY target/companyms.jar companyms.jar

		# Step 4: Expose the port the app runs on
EXPOSE 8081

		# Step 5: Run the JAR file
ENTRYPOINT ["java", "-jar", "companyms.jar"]