# AutomationExercise

AutomationExercise is a modern, modular Java automation testing framework for web applications. It demonstrates best practices in UI automation, advanced error handling, and maintainable design patterns, making it an excellent reference for both learning and real-world use.

---

## About This Project

This project is a comprehensive hands-on exercise in software test automation. It covers automation of common web application scenarios such as user registration, login, product browsing, cart management, and checkout. The codebase is modular, maintainable, and easy to extend, reflecting scalable automation design principles. It is designed to showcase advanced test automation skills and serve as a reference for building scalable and robust automation frameworks.

---

[Quick Start](#getting-started) | [Features](#features-at-a-glance) | [Project Structure](#project-structure) | [How to Extend](#how-to-extend) | [Contribution](contribution.md) | [FAQ](#troubleshooting--faq)

---

## Why Use This Project?

- **Comprehensive**: Automates real-world e-commerce user journeys (signup, login, cart, checkout, etc.)
- **Robust**: Resilient to UI changes (Healenium), flakiness handling (ad removal), and strong negative case validation
- **Developer Friendly**: Clean, modular structure with reusable helpers, properties-driven test data, and clear logging
- **Professional Reporting**: Allure integration for beautiful, actionable test reports
- **CI/CD Ready**: Easily integrates with Jenkins or any modern CI pipeline

---

## Features at a Glance

| Feature                  | Description                                                                  |
|--------------------------|------------------------------------------------------------------------------|
| User Registration        | Signup with validation for errors, blanks, duplicates                        |
| Login/Logout             | Positive/negative, blank credentials, session management                     |
| Product Browsing/Search  | By name, invalid/empty search, details, price check                          |
| Cart Management          | Add/remove products, total validation, persistence, empty state handling      |
| Checkout & Payment       | Valid/invalid/empty payment flows, order placement, delivery checks           |
| Newsletter Subscription  | Valid/invalid/empty email, UI error validation                               |
| Negative Testing         | Covers all flows for invalid/boundary cases                                  |
| Self-Healing Driver      | Healenium adapts to minor UI changes                                         |
| Ad Removal Utility       | JS-based, reduces test flakiness                                             |
| Allure Reporting         | Interactive, visual test reports                                             |
| CI/CD Integration        | Jenkins-ready, with Maven/Allure tasks                                       |
| Cross-Browser Ready      | Chrome (default), extendable to others via WebDriverManager                  |
| Modular Extensibility    | Add flows, page objects, data, or utilities with minimal effort              |
| Structured Logging       | Log4j-powered, for easy trace/debug                                          |
| Screenshot on Failure    | Captures failed test state for diagnosis                                     |

---

## Sample Allure Report

*Example of the interactive Allure report generated after test runs:*

![Allure Report Example](docs/allure-sample.png)  
*For full reports, run `mvn allure:serve` after executing tests.*

---

## Getting Started

### Prerequisites

- **Java** 17 or higher  
- **Maven** (latest recommended)  
- **Google Chrome** (default, extendable)  
- **Allure** (for reporting, see [Allure install guide](https://docs.qameta.io/allure/#_installing_a_commandline))

### Installation

```sh
git clone https://github.com/Mahiimhetre/AutomationExercise.git
cd AutomationExercise
mvn clean install
```

**Configure Your Test Data:**  
Edit `src/test/java/Framework/testData/AutomationExcercise.properties` for URLs, credentials, emails, etc.

### Running Tests

```sh
mvn test
```
Or, run specific classes via your IDE (TestNG).

### Generating Allure Reports

```sh
mvn allure:serve
```

---

## Project Structure

```
AutomationExercise/
└── src/
    └── test/
        └── java/
            └── Framework/
                ├── Elements/      # Page Object Model classes for each UI page
                ├── Functions/     # Business logic and reusable actions for features
                ├── testCases/     # TestNG test classes for each feature
                ├── SuiteFiles/    # TestNG XML suite files for grouping and running tests
                └── testData/      # Properties file for test data
```

---

## Example Test Scenario

```java
@Test(description = "Verify checkout process for logged-in user")
public void checkout() throws Exception {
    login.loginWithValidCreds();
    cart.addProducts(1);
    cart.proceedToCheckout();
    checkout.fillPaymentDetails("valid");
    checkout.confirmOrder();
    Assert.assertTrue(order.isConfirmed());
}
```

---

## How to Extend

- **Add a Page Object:** Place new page class in `Elements/`
- **Add Business Logic:** Implement in `Functions/`
- **Write a Test:** Add in `testCases/` and reference in suite XML as needed
- **More Test Data:** Add to/modify the properties file in `testData/`
- **Integrate a New Browser:** Update WebDriverManager logic in the framework

---

## CI/CD Integration

**Sample Jenkins Pipeline Step:**

```groovy
pipeline {
  agent any
  stages {
    stage('Build & Test') {
      steps {
        sh 'mvn clean test'
      }
    }
    stage('Allure Report') {
      steps {
        sh 'mvn allure:serve'
      }
    }
  }
}
```
*Configure Maven, Java, and Allure plugins on your agent as needed.*

---

## Troubleshooting & FAQ

- **Browser/driver errors**: Check Maven/WebDriverManager versions.
- **Flaky tests (ads)**: Framework auto-removes ads; update selectors if site layout changes.
- **Allure not displaying**: Ensure Allure CLI is installed and JAVA_HOME is set.
- **Jenkins issues**: Ensure all dependencies/tools are available on the Jenkins agent.

---

## Contribution

PRs and issues are welcome!  
Please see our [Contribution Guidelines](CONTRIBUTING.md) for details on submitting pull requests, coding standards, and how to help with documentation or tests.

---

## Supported Platforms

- **OS:** Windows, macOS, Linux
- **Browsers:** Chrome (default). Extendable to Firefox, Edge, etc.

---

## Author & Support

[Mr. Mahesh Mhetre](https://github.com/Mahiimhetre)  
For support, open a GitHub issue or contact via [email@example.com](mailto:email@example.com)

---
