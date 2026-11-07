
# Stage 1: Build Spring Boot application
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build JAR file
RUN mvn clean package -DskipTests

# Stage 2: Run Spring Boot application
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 2503

ENTRYPOINT ["java", "-jar", "app.jar"]
