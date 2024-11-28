# Book Review Application

A RESTful web application for managing book reviews built with Spring Boot, Hibernate/JPA, and Swagger/OpenAPI.

## Features

- Create Book Review
- Get All Book Reviews
- Get Book Review by ID
- Update Book Review
- Delete Book Review

## Project Structure

- **Entity**: Contains the BookReview entity class
- **Repository**: BookReviewRepository interface for CRUD operations
- **Service**: Business logic for managing book reviews
- **Controller**: REST endpoints for API interaction
- **DTOs**:
    - BookReviewCreateDTO
    - BookReviewUpdateDTO
    - BookReviewResponseDTO

## Prerequisites

- Java 17+
- Maven
- MySQL

## Setup Instructions

### Clone the Repository

```bash
git clone https://github.com/your-repo/book-review-app.git
cd book-review-app
```

### Configure Database

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/book_reviews_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Build the Application

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

## API Endpoints

- **Create a Book Review**: `POST /api/book-reviews`
- **Get All Book Reviews**: `GET /api/book-reviews`
- **Get Book Review by ID**: `GET /api/book-reviews/{id}`
- **Update Book Review**: `PUT /api/book-reviews/{id}`
- **Delete Book Review**: `DELETE /api/book-reviews/{id}`

### API Documentation

Access Swagger UI: `http://localhost:8080/api`