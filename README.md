# MyInternship Backend

Backend for MyInternship, built with Java Spring Boot and deployed on Render with PostgreSQL.

## Live API
- Base URL: [https://internship-tracker-xo8m.onrender.com](https://internship-tracker-xo8m.onrender.com)
- Health Check: [https://internship-tracker-xo8m.onrender.com/api/health](https://internship-tracker-xo8m.onrender.com/api/health)

## Repository Scope
This repository contains only the backend service:
- REST API endpoints
- Authentication and authorization
- Business logic and validation
- Persistence and data access layer
- Security, CORS, and deployment configuration

Frontend repo:
- [https://github.com/ansonnchan/internship-tracker-frontend](https://github.com/ansonnchan/internship-tracker-frontend)

## Architecture Overview
`Client (Vercel)` -> `Spring Boot API (Render)` -> `PostgreSQL`  
Auth model: `JWT Bearer token` for protected endpoints.

## Core Features
- User signup/login with JWT issuance
- Protected user-scoped application CRUD endpoints
- Application status management
- Email classification endpoints
- Validation + global exception handling
- CORS policy for local and production clients
- Health endpoint for deployment verification

## Project Structure
```text
internshiptracker/
|-- src/main/java/com/anson/internshiptracker/
|   |-- controller/               # REST controllers
|   |-- service/                  # Business logic
|   |-- repository/               # JPA repositories
|   |-- model/                    # Entities + enums
|   |-- security/                 # Security config + JWT filter
|   |-- util/                     # JWT / helper utilities
|   `-- exception/                # Exception model + handlers
|-- src/main/resources/
|   `-- application.properties    # Runtime config
|-- pom.xml
|-- Dockerfile
`-- .env.local
