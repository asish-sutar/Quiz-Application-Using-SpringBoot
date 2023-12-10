Certainly! Here's the complete README content in a single Markdown page:

```markdown
# Quiz Application

This repository contains a simple Quiz application implemented in Java using the Spring Boot framework. The application allows users to create quizzes with questions, options, and set start and end dates. Users can also retrieve information about all quizzes, get details of an individual quiz, and check the result of a quiz after it has ended.

## Setup

### Database Configuration

The application uses MySQL as the database. Configure your database settings in the `application.properties` file located in the `src/main/resources` directory.

```properties
# Server port
server.port=5050

# Enable or disable the automatic generation of DDL scripts
spring.jpa.generate-ddl=true

# Disable the banner during application startup
spring.main.banner-mode=off

# DataSource Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/github
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

### Run the Application

Clone the repository and navigate to the project directory. Run the application using the following command:

```bash
./mvnw spring-boot:run
```

The application will start, and you can access it at [http://localhost:5050](http://localhost:5050).

## Usage

### Create a Quiz

To create a quiz, use the following endpoint with a POST request:

```json
POST /api
{
  "question": "What is the capital of France?",
  "options": ["Berlin", "Madrid", "Paris", "Rome"],
  "rightAnswer": 2,
  "startDate": "2023-01-01T10:00:00",
  "endDate": "2023-01-01T11:00:00",
  "status": "ACTIVE"
}
```

### Get All Quizzes

Retrieve information about all quizzes with a GET request:

```json
GET /api/getAll
```

### Get Active Quizzes

Retrieve information about active quizzes with a GET request:

```json
GET /api/active
```

### Get Quiz Result

Retrieve the result of a quiz by providing its ID with a GET request:

```json
GET /api/result/{id}
```

Make sure to replace `{id}` with the actual ID of the quiz.

## Project Structure

### Model

- `Quiz`: Represents a quiz with a question, options, right answer, start and end dates, and status.
- `QuizStatus`: Enum representing the status of a quiz (INACTIVE, ACTIVE, FINISHED).

### Repository

- `QuizRepo`: JpaRepository for interacting with the database and managing quizzes.

### Service

- `QuizService`: Interface defining quiz-related operations.
- `QuizServiceImpl`: Implementation of the `QuizService` interface.

### Controller

- `QuizController`: REST controller handling quiz-related HTTP requests.

Feel free to explore and modify the code as needed for your specific requirements.
```

Copy and paste this content into your README.md file on GitHub. Adjustments can be made based on your specific needs or preferences.
