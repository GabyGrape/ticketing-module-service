# Step 1: Build stage menggunakan Maven dan OpenJDK 24
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy poms dan source code
COPY pom.xml .
COPY src ./src

# Build aplikasi menjadi JAR tanpa menjalankan unit test
RUN mvn clean package -DskipTests

# Step 2: Run stage menggunakan Java Runtime
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy file JAR hasil build dari stage 1
COPY --from=build /app/target/*.jar app.jar

# Expose port aplikasi (sesuai log aplikasi kamu: 8080)
EXPOSE 8080

# Jalankan aplikasi Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]