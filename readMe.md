
# Rule Engine

The **Rule Enginer** is a web application that allows users to create rules,
evaluate them against provided data,
and manage existing rules.
This application is built using HTML, CSS, and JavaScript for the frontend, with a Spring Boot backend that handles API requests.



## Features

- Create new rules with a name and a logical expression.
- Select existing rules from a dropdown menu.
- Input user data (age, salary, department, experience) for evaluation.
- Evaluate selected rules against the provided data.
- Display results of evaluations in real-time.


## Tech Stack

- **Frontend**: HTML, CSS, JavaScript
- **Backend**: Spring Boot
- **Database**: MySql
- **API**: RESTful services

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- A web browser




### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/rule-evaluator.git
   cd rule-evaluator
   ```

2. **Backend Setup**:
    - Navigate to the backend directory:
      ```bash
      cd rule-engine
      ```
    - Build the Spring Boot application:
      ```bash
      mvn clean install
      ```
    - Run the Spring Boot application:
      ```bash
      mvn spring-boot:run
      ```

### Accessing the Application

- The frontend can be accessed at: [http://localhost:8080](http://localhost:8080)


## API Reference

### 1. Create rule

- **Endpoint**: `/api/rules/create`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
      "ruleName": "Your Rule Name",
      "ruleString": "(age > 30 AND department = 'Sales')"
  }
  ```
- **Response**: Returns the created rule Abstract Syntax Tree.

### 2. Get all rules

- **Endpoint**: `/api/rules`
- **Method**: `GET`
- **Response**: Returns a List of all rules with their IDs ,names and Rule Strings.


### 3. Get rule by id

- **Endpoint**: `/api/rule/{id}`
- **Method**: `GET`
- **Response**: Returns the rule by it's id.


### 4. Evaluate Rule

- **Endpoint**: `/api/rules/evaluate`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
      "ruleId": 1,
      "data": {
          "age": 35,
          "salary": 60000,
          "department": "Sales",
          "experience": 5
      }
  }
  ```
- **Response**: Returns a boolean indicating if the evaluation was successful.




## Usage/Examples

1. Open the application in your web browser at [http://localhost:8080](http://localhost:8080).
2. Create a new rule by entering a name and a logical expression.
3. Select an existing rule from the dropdown menu.
4. Enter user data for evaluation.
5. Click the submit button to see the evaluation result.


## Error Handling
The application includes basic error handling for both frontend validation and backend processing. If any errors occur during rule creation or evaluation, appropriate messages will be displayed to the user.
