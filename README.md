Messenger 2025 — Backend 🚀
A production-oriented Spring Boot backend powering the Messenger 2025 app. Provides REST APIs, STOMP/WebSocket real‑time messaging, persistent storage with MySQL, and JWT-based authentication validation.

🔧 Tech stack
Spring Boot 3.x (REST controllers & WebSocket endpoints)
MySQL 8+ (persistent storage)
JPA / Hibernate (ORM)
STOMP over WebSocket (real‑time messaging)
Lombok (boilerplate reduction)
JWT (access validation)
🏗 Architecture overview
Controller (REST & WebSocket)
Service (business logic)
Repository (data access)
Model / DTO (payloads and entities)

✅ Features
REST endpoints for users and messages (GET/POST)
Real‑time chat using WebSocket + STOMP
DTOs for clean API payloads
Service layer for testable business logic
JWT validation for protected endpoints
Note: Frontend authenticates with Clerk and sends JWTs in Authorization: Bearer <token> headers. Backend validates tokens (server-side validation filter / Spring Security).

🚀 Quick start
Prereqs:

Java 21+
MySQL 8+
Run locally:

⚙️ Environment example
Add DB credentials to application.properties:

📌 Key endpoints
GET /api/users — List users
POST /api/users — Create user
GET /api/messages — List messages
POST /api/messages — Send message
WebSocket: /ws — Real‑time chat (STOMP)

🤝 Contributing
Fork → branch (git checkout -b [Messenger_2025](http://_vscodecontentref_/1).) → commit → PR
Follow existing code patterns and add/update tests where applicable

📬 Contact
Created by Tharindu Silva — feel free to reach out for questions or collaboration.

