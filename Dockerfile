# Use Maven with JDK 17 for build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the project (skip tests to avoid running them during build)
RUN mvn clean package -DskipTests

# Use a smaller JDK image for running tests
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy built project from build stage
COPY --from=build /app .

# Run TestNG suite
CMD ["mvn", "test", "-DsuiteXmlFile=src/test/java/Framework/SuiteFiles/masterSuite.xml"]