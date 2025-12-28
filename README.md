Messenger 2025 — Backend 🚀

A production-oriented Spring Boot backend powering the Messenger 2025 application.
This service delivers REST APIs, real-time messaging via WebSockets, persistent storage with MySQL, and JWT-based authentication validation.

🔧 Tech Stack

Spring Boot 3.x — REST controllers & WebSocket endpoints

MySQL 8+ — Persistent relational storage

JPA / Hibernate — ORM & data mapping

STOMP over WebSocket — Real-time messaging

Lombok — Boilerplate reduction

JWT — Access token validation

🏗 Architecture Overview
Controller (REST & WebSocket)
        ↓
Service (Business Logic)
        ↓
Repository (Data Access)
        ↓
Model / DTO (Entities & API Payloads)

✅ Features

REST endpoints for users and messages (GET / POST)

Real-time chat using WebSocket + STOMP

DTO-based APIs for clean and stable payloads

Service layer for testable and maintainable business logic

JWT validation for protected endpoints

Authentication Note
The frontend authenticates users using Clerk and sends JWTs via
Authorization: Bearer <token> headers.
The backend performs server-side JWT validation using Spring Security filters.

🚀 Quick Start
Prerequisites

Java 21+

MySQL 8+

⚙️ Environment Configuration

Add your database credentials to application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/messenger_2025
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

▶️ Run Locally
./mvnw spring-boot:run

📌 Key Endpoints
REST APIs

GET /api/users — List users

POST /api/users — Create user

GET /api/messages — List messages

POST /api/messages — Send message

WebSocket

/ws — Real-time chat (STOMP)

🤝 Contributing

Fork the repository

Create a new branch

git checkout -b Messenger_2025


Commit your changes

Open a Pull Request

Please follow existing code patterns and add or update tests where applicable.

📬 Contact

Created by Tharindu Silva
Feel free to reach out for questions, feedback, or collaboration.
