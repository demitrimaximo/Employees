# Employee Management API
REST API for employee management, developed with Spring Boot as part of the technical selection process for INVEX.

## Technical Requirements
* Java 17
* Spring Boot 2.7.18
* Maven
* H2 Database
* Docker (optional)

## Technologies
* **Java 17**
* **Spring Boot 2.7.18**
* **Spring Data JPA / Hibernate**
* **H2 Database**  in-memory database for testing
* **Lombok**
* **Swagger / OpenAPI 3**  API documentation
* **JUnit 5 / Mockito**  unit testing
* **Maven**  dependency management
* **Spring Boot Actuator**  health checks and application metrics

## Getting Started

### Prerequisites

Make sure you have the following installed:

* Java 17
* Maven 3.x
* Git

Docker is optional if you want to run the application in a container.

### Clone the Repository

https://github.com/demitrimaximo/Employees.git
cd PruebaTecnicaInvex

### Build the Application

mvn clean install

### Run the Application

mvn spring-boot:run -DskipTests

The application will start on:
http://localhost:8080


## API Documentation
Swagger UI is available at:
http://localhost:8080/swagger-ui/index.html

## REST API Endpoints
| Method | Endpoint              | Description              |
| ------ | --------------------- | -------------------------|
| GET    | /employees            | Get all employees        |
| GET    | /employees/{id}       | Get an employee by ID    |
| POST   | /employees            | Create a new employee(s) |
| PUT    | /employees/{id}       | Update an employee       |
| DELETE | /employees/{id}       | Delete an employee       |

Check Swagger UI for request/response schemas and detailed API documentation.

## API Testing

A Postman collection called  APIcollectionsforEmployee.json is included in the postman/ directory.
Import the collection into Postman to test the API endpoints.

## Database
The application uses an H2 in-memory database, which is mainly intended for testing and development purposes.
Database configuration can be found in:
src/main/resources/application.yml

## Testing
To execute the unit tests:
mvn test
The project uses **JUnit 5** and **Mockito** for unit testing.

## Health Check

Spring Boot Actuator provides a health endpoint:

http://localhost:8080/actuator/health

## Notes
This project was developed as a technical exercise for the INVEX selection process, following common REST API, Spring Boot, JPA, validation, exception handling, and testing practices.
