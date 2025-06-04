# AutomationExercise

**AutomationExercise** is an automation testing framework for web applications, built using Java, Selenium WebDriver, and TestNG. This project demonstrates best practices in automated UI testing, following the Page Object Model (POM) design pattern. The framework is designed and implemented by me as a practical showcase of my skills in test automation.

---

## About This Project

This project serves as my personal hands-on experience in software test automation. It covers automation of common web application scenarios such as user registration, login, product browsing, cart management, and checkout. The codebase is modular, maintainable, and easy to extend, reflecting my understanding of scalable automation design.

---

## Tools & Technologies Used

- **Java**  
  Main programming language for writing automation scripts and framework components.

- **Selenium WebDriver**  
  For automating browser interactions and UI testing.

- **TestNG**  
  Testing framework for organizing, grouping, and reporting test execution.

- **Allure Reports**  
  For generating advanced, visually appealing, and interactive test execution reports.

- **Maven**  
  For project build, dependency management, and running tests.

- **Jenkins**  
  For continuous integration (CI), automated build, and scheduled test execution.

- **Page Object Model (POM)**  
  Design pattern used to create reusable and maintainable page classes for UI elements.

- **WebDriverManager**  
  Automatically manages browser driver binaries (no manual download required).

- **Log4j**  
  For logging test execution steps and debugging information.

- **Properties Files**  
  To manage test data, URLs, credentials, and other environment configurations.

- **Google Chrome (Browser)**  
  Main browser used for running automation tests.

- **IntelliJ IDEA / Eclipse (Recommended)**  
  IDEs for development, running, and debugging the automation scripts.

---

## Project Structure

```
AutomationExercise/
└── src/
    └── test/
        └── java/
            └── Framework/
                ├── Elements/      # Page Object Model classes for each UI page
                │   
                ├── Functions/     # Business logic and reusable actions for features
                │
                ├── testCases/     # TestNG test classes for each feature
                │  
                ├── SuiteFiles/    # TestNG XML suite files for grouping and running tests
                │  
                └── testData/      # Properties file for test data
```

---

## What This Project Demonstrates

- Setting up and configuring Selenium WebDriver with Java.
- Using the Page Object Model (POM) for scalable automation code.
- Writing and organizing tests using TestNG.
- Generating interactive reports with Allure.
- Integrating with Jenkins for CI/CD and automated test execution.
- Creating reusable helper methods for automation needs.
- Managing test data using properties files.
- Validating both positive and negative scenarios for real web applications.

---

## Getting Started

### Prerequisites

- Java 17 or higher installed 
- Maven installed
- Google Chrome browser

### Setup Instructions

1. **Clone the Repository**
    ```sh
    git clone https://github.com/Mahiimhetre/AutomationExercise.git
    cd AutomationExercise
    ```

2. **Install Dependencies**
    ```sh
    mvn clean install
    ```

3. **Configure Test Data**
    - Edit `src/test/java/Framework/testData/AutomationExcercise.properties` with relevant test URLs, usernames, and passwords.

4. **Run the Tests**
    - Using Maven:
      ```sh
      mvn test
      ```
    - Or run individual test classes from your IDE (TestNG supported).

5. **View Allure Reports**
    - After test execution, generate and open the Allure report:
      ```sh
      mvn allure:serve
      ```

6. **Continuous Integration with Jenkins**
    - Configure Jenkins to pull this repository, execute Maven builds, and publish Allure reports as part of your CI pipeline.

---

## Example Test Scenarios

- **Home Page:** Page title, navigation, newsletter subscription.
- **Signup:** Successful registration, duplicate/case-sensitive email handling, blank field checks.
- **Login:** Success and failure cases, blank input checks, logout flow.
- **Cart & Checkout:** Adding to cart, validating checkout process, delivery address validation.
- **Product Browsing:** Product search, selection, and validation.
- **Negative Testing:** Error message validation, required field checks, invalid input handling.

---

## Author

[Mahiimhetre](https://github.com/Mahiimhetre)

---
