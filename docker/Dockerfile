# Select base image
FROM openjdk:8

# Copy target jar app
COPY animalshelter.jar /usr/src/app/

# Port to expose
EXPOSE 8443

# Command for running the app
CMD ["java", "-jar", "/usr/src/app/animalshelter.jar"]
