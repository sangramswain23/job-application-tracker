# Job Application Tracker – Backend

Spring Boot backend for tracking job applications, interview stages, and outcomes with domain-level validation and status history.

## Core Features
- Create and manage job applications
- Application status lifecycle with enforced transitions
- Status history tracking for auditing
- Clean layered architecture (Controller, Service, Repository)
- Global exception handling

## Tech Stack
**Backend:** Java 17, Spring Boot, Spring Data JPA, Spring Security
**Database:** PostgreSQL
**Tools:** Maven, Postman, STS, GitHub

## Application Status Flow
```
APPLIED → SHORTLISTED → INTERVIEW → OFFERED
                            ↓
                         REJECTED
```

Invalid transitions are blocked at the domain level.

## API Overview
* **POST** /api/applications — Create a job application
* **PUT** /api/applications/{id}/status — Update application status
* **GET** /api/applications/user/{userId} — Get all applications for a user
* **GET** /api/applications/user/{userId}/status/{status} — Filter applications by status

## Project Architecture

```
/job-application-tracker
 ├── src/main/java
 │    ├── controller
 │    ├── service
 │    ├── service/impl
 │    ├── repository
 │    ├── entity
 │    ├── enums
 │    ├── dto
 │    ├── exception
 │    └── config
 ├── src/main/resources
 │    └── application.properties
 ├── pom.xml
```

## Running the Project

1. Update database configuration in `application.properties`
2. Run the application:
```
mvn spring-boot:run
```
3. Test APIs using Postman
Base URL:
```
http://localhost:8080
```
## Highlights
- Domain-driven status transition validation
- Status history persistence
- Clean separation of concerns (Controller–Service–Repository)
- Transaction-safe service layer

## Author

Sangram Swain
