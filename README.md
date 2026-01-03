# Spring Boot Authentication Service

A production-ready authentication and authorization service built using **Spring Boot** with **JWT**, **role-based access control**, and **token persistence**.  

---

## 🚀 Features

- User Registration
- Login using **username or email**
- Password encryption using **BCrypt**
- JWT-based authentication
- Role-based authorization (ADMIN, USER)
- JWT token persistence in database
- Logout with token revocation
- Secure configuration using **YAML & profiles**

---


## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- PostgreSQL
- Hibernate / JPA
- Maven

---
## 📂 Project Structure
```
src/main/java/com/example/rls
-├── config # Security configuration
-├── controller # REST controllers
-├── dao # Request/Response DTOs
-├── entity # JPA entities
-├── jwt # JWT utilities & filters
-├── repository # JPA repositories
-├── service # Service interfaces
-├── serviceImpl # Service implementations
-└── RlsApplication # Main application
```
---

## 🔐 Security Design

- Passwords are encrypted using **BCrypt**
- JWT contains:
  - username
  - roles
  - issued & expiry time
- Every issued token is stored in the database
- Logout marks token as **expired & revoked**
- Secured endpoints are protected using Spring Security filters

---

## ⚙️ Configuration Management

- Application configuration is managed using **`application.yml`**
- Sensitive values (DB credentials, JWT secret) are **externalized**
- Local secrets are kept in `application-local.yml` (gitignored)

Example configuration:
```yaml
spring:
  datasource:
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

jwt:
  secret: ${JWT_SECRET}
```
---

## 📌 API Overview (High Level)

### Authentication APIs
- `POST /api/auth/register` – User registration
- `POST /api/auth/login` – Login & JWT generation
- `POST /api/auth/logout` – Logout & token revocation

### Role Management APIs
- `POST /api/roles` – Create role (ADMIN only)

### User Management APIs
- `GET /api/users` – Fetch users (secured)

---

## 🧪 Testing

- Basic context load test included
- APIs tested using Postman
- JWT validation verified end-to-end


---

## 👤 Author

**Abhishek Musale**  
Java Developer

---
