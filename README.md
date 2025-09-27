# Messenger 2025 Backend 🚀

A robust Spring Boot backend powering the Messenger 2025 app. Handles REST APIs, real-time messaging, authentication, and data persistence.

---

## Architecture Overview

- **Spring Boot 3.5** for REST APIs and WebSocket endpoints
- **MySQL** for persistent storage
- **JPA/Hibernate** for ORM
- **Lombok** for boilerplate reduction
- **STOMP/WebSocket** for real-time chat
- **Layered structure:**
  - Controller (REST & WebSocket)
  - Service (business logic)
  - Repository (data access)
  - Model/DTO (data objects)

---

## Security & JWT Implementation

- **Planned:** Secure endpoints using JWT authentication
- **How:**
  - Frontend authenticates with Clerk and receives JWT
  - JWT sent with API requests in `Authorization: Bearer <token>` header
  - Backend validates JWT using a filter/interceptor
  - Only authenticated users can access protected endpoints
- **Spring Security** will be configured for JWT validation and role-based access

---

## Features

- RESTful APIs for users and messages
- Real-time messaging via WebSocket (STOMP)
- DTOs for clean payloads
- Service layer for business logic
- Repository layer for DB access

---

## Getting Started

### Prerequisites
- Java 21+
- MySQL 8+

### Setup
1. Clone the repo
2. Configure `src/main/resources/application.properties` with your DB credentials
3. Build and run:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## Environment Variables

Configure in `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/messenger_db
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
```

---

## Project Structure

```
src/main/java/com/example/messenger_2025/
  controller/   # REST & WebSocket controllers
  service/      # Business logic
  repository/   # Data access
  model/        # Entities
  payload/      # DTOs
src/main/resources/
  application.properties
```

---

## Key Endpoints

- `GET /api/users` — List users
- `POST /api/users` — Create user
- `GET /api/messages` — List messages
- `POST /api/messages` — Send message
- WebSocket `/ws` — Real-time chat

---

## Contributing

1. Fork the repo
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---


## Contact

Created by [Tharindu Silva](https://github.com/TharinduSilva2019774) — feel free to reach out!
