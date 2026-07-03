# 🎭 Playwright Java RESTful Booker Framework
![Java](https://img.shields.io/badge/Java-17-orange)
![Playwright](https://img.shields.io/badge/Playwright-Java-green)
![REST Assured](https://img.shields.io/badge/REST_Assured-API_Testing-success)
![TestNG](https://img.shields.io/badge/TestNG-Testing-red)
![Maven](https://img.shields.io/badge/Maven-Build-blue)
![GitHub](https://img.shields.io/badge/GitHub-Version_Control-black)
![Jenkins](https://img.shields.io/badge/Jenkins-CI/CD-red)
![License](https://img.shields.io/badge/License-MIT-blue)

> A production-ready automation framework for **UI Testing** and **REST API Testing**...

> A production-ready automation framework for **UI Testing** and **REST API Testing** built using **Playwright Java**, **TestNG**, **Maven**, and **REST Assured**, following enterprise automation best practices.

---

## 🚀 Project Overview

This project is an enterprise-grade automation framework developed for automating both web UI and REST API testing. It follows a scalable and maintainable architecture using the Page Object Model (POM), reusable utilities, centralized configuration, logging, reporting, and CI/CD integration.

The framework is designed to demonstrate industry-standard automation practices and can be used as a reference for enterprise automation projects, technical interviews, and portfolio showcases.
---

# 🏗 Framework Architecture

The framework follows a layered architecture to ensure scalability, maintainability, and reusability. It separates UI automation, API automation, utilities, reporting, and CI/CD into independent components.

```mermaid
flowchart TD

    A[TestNG Suite Files]

    A --> B[UI Test Classes]
    A --> C[API Test Classes]

    B --> D[Playwright Java]
    D --> E[Page Object Model]

    C --> F[REST Assured]

    E --> G[Utilities]
    F --> G

    G --> H[Configuration]
    G --> I[Logger]
    G --> J[Test Data]
    G --> K[Screenshots]

    H --> L[Reports]
    I --> L
    J --> L
    K --> L

    L --> M[Allure Reports]
    L --> N[Extent Reports]

    M --> O[Jenkins CI/CD]
    N --> O

    O --> P[GitHub Repository]
```## 📖 Architecture Layers

### 1️⃣ Test Execution Layer

- TestNG Suite XML files control test execution.
- UI and API suites can run independently.
- Supports parallel execution.

---

### 2️⃣ UI Automation Layer

- Developed using **Playwright Java**.
- Uses the **Page Object Model (POM)**.
- Keeps page locators and actions separate from test logic.

---

### 3️⃣ API Automation Layer

- Developed using **REST Assured**.
- Supports complete CRUD operations.
- Validates status codes, headers, cookies, and response bodies.

---

### 4️⃣ Utility Layer

Reusable utilities include:

- Configuration Reader
- Logger
- Screenshot Utility
- Wait Utility
- File Utility
- Data Provider
- Common Helpers

---

### 5️⃣ Reporting Layer

After execution, the framework automatically generates:

- TestNG Reports
- Allure Reports
- Extent Reports

---

### 6️⃣ CI/CD Layer

The framework integrates with:

- GitHub
- Jenkins
- Maven

for automated build and test execution.
---

## ✨ Key Features

- UI Automation using Playwright Java
- REST API Automation using REST Assured
- Page Object Model (POM)
- TestNG Test Execution
- Maven Project Management
- Cross-browser Support
- Data-Driven Testing
- Reusable Utility Classes
- Configuration Management
- Log4j2 Logging
- Screenshot Capture on Failure
- Allure Reporting
- Extent Reporting
- Parallel Test Execution
- Jenkins CI/CD Integration
- GitHub Version Control
- Clean Project Architecture

---

## 🛠 Technology Stack

| Technology | Purpose |
|------------|---------|
| Java | Programming Language |
| Playwright | UI Automation |
| REST Assured | API Automation |
| TestNG | Test Framework |
| Maven | Build Tool |
| Log4j2 | Logging |
| Allure Report | Test Reporting |
| Extent Reports | HTML Reporting |
| Jenkins | Continuous Integration |
| Git & GitHub | Version Control |