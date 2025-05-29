# Build stage using Maven with JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first for caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and package it (skip tests during build)
COPY src ./src
RUN mvn clean package -DskipTests

# Final stage - use the same image so mvn is available
FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

# Copy built artifacts from build stage
COPY --from=build /app .

# Default command to run your TestNG suite
CMD ["mvn", "test", "-DsuiteXmlFile=src/test/java/Framework/SuiteFiles/masterSuite.xml"]
