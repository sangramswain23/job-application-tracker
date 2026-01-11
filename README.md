# Job Application Tracker – Backend

Spring Boot backend for tracking job applications, interview stages, and outcomes with domain-level validation and status history.

## Tech Stack
- Java 17
- Spring Boot 
- Spring Data JPA
- Spring Security
- MySQL
- Maven

## Core Features
- Create and manage job applications
- Application status lifecycle with enforced transitions
- Status history tracking for auditing
- Clean layered architecture (Controller, Service, Repository)
- Global exception handling

## Application Status Flow
APPLIED → SHORTLISTED → INTERVIEW → OFFERED / REJECTED

Invalid transitions are blocked at the domain level.

## APIs
- POST /api/applications
- PUT /api/applications/{id}/status
- GET /api/applications/user/{userId}
- GET /api/applications/user/{userId}/status/{status}

## Highlights
- Domain-driven status transition enforcement
- Status history persistence
- Transaction-safe service layer
