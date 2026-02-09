# 💬 Messenger 2025 – Backend (Spring Boot)

A modern, scalable real-time messaging backend built with Spring Boot, WebSockets (STOMP), JWT security, and MySQL.  
Designed with clean architecture principles and seamless integration with a Clerk-authenticated frontend.

---

## 🧱 Architecture Overview

### Layer Responsibilities

- Controllers — Handle HTTP requests and WebSocket connections  
- Services — Implement business logic and enforce data integrity  
- Repositories — Manage database access and transactions  
- Models / DTOs — Define entities and API request/response payloads  

---

## ✅ Features

- ✅ REST APIs — Create, retrieve, update, and delete users & messages  
- ✅ Real-time Chat — WebSocket + STOMP for instant messaging  
- ✅ Clean API Design — DTO-based request/response models  
- ✅ Testable Services — Decoupled business logic  
- ✅ Secure Endpoints — JWT-based authentication with Spring Security  
- ✅ Clerk Integration — Frontend authenticates via Clerk, backend validates JWT  

Note:  
The frontend authenticates users using Clerk and sends JWTs in  
Authorization: Bearer &lt;token&gt; headers.  
The backend validates these tokens via Spring Security filters.

---

## 📌 Prerequisites

- Java 21+  
- Maven 3.8+ (or Gradle)  
- MySQL 8.0+  
- Git  

Optional:
- Docker (for MySQL container)
- Postman or Thunder Client

---

## 🚀 Installation & Setup

### 1. Clone the Repository

git clone https://github.com/TharinduSilva2019774/messenger_2025_BE.git  
cd messenger_2025_BE

---

### 2. Set Up MySQL Database

Using Docker (Recommended):

docker run --name messenger_db \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=messenger_2025 \
-p 3306:3306 \
-d mysql:8.0

Or manually:

CREATE DATABASE messenger_2025;

---

### 3. Application Properties

spring.datasource.url=jdbc:mysql://localhost:3306/messenger_2025  
spring.datasource.username=root  
spring.datasource.password=root  

spring.jpa.hibernate.ddl-auto=update  

server.port=8080  
server.servlet.context-path=/api  

jwt.secret=your_super_secret_jwt_key_here  
jwt.expiration=86400000  

spring.websocket.path=/ws  

---

### 4. Build & Run

mvn clean install  
mvn spring-boot:run  

Application URL: http://localhost:8080

---

## 📡 API Documentation

### User Endpoints

GET    /api/users  
POST   /api/users  
GET    /api/users/{id}  
PUT    /api/users/{id}  
DELETE /api/users/{id}  

---

### Message Endpoints

GET    /api/messages  
GET    /api/messages?userId={id}  
POST   /api/messages  
DELETE /api/messages/{id}  

---

## 🔌 WebSocket

Endpoint: ws://localhost:8080/ws  

Subscribe:
'/user/queue/messages'

Send:
'/app/chat.sendMessage'

---

## 📂 Project Structure

messenger_2025_BE/
├── controller
├── service
├── repository
├── model
├── dto
├── security
└── MessengerApplication.java

---

## 🤝 Contributing

- Fork the repo  
- Create a feature branch  
- Commit changes  
- Open a Pull Request  

---

## 📄 License

MIT License

---

## 📬 Contact

Created by: Tharindu Silva  
GitHub: @TharinduSilva2019774  

Last Updated: February 2026
