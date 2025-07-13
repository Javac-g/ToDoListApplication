
<div id="header">
  <img src="https://github.com/Javac-g/ToDoListApplication/blob/main/TMS.png?raw=true"/>
</div>
# ✅ To-Do List REST API

This project is a secure RESTful backend API for managing personal to-do lists and team collaboration tasks. It supports operations on users, their todos, collaborators, and individual tasks. This system can serve as the backend for a task management web or mobile application.

---

## 🔧 Features

- ✅ Full CRUD support for:
  - Users
  - Todos
  - Tasks
  - Collaborators
- 🔐 Secure endpoints using **JWT-based authentication**
- 🔒 Role-based access control (ADMIN and USER)
- 📡 RESTful architecture with meaningful HTTP methods (GET, POST, PUT, DELETE)
- ⚠️ Custom exception handling with `ResponseStatusException`
- 🧪 Predefined sample users for testing
- 📁 Clean RESTful resource hierarchy

---

## 📌 API Endpoints

| Resource | Endpoint |
|----------|----------|
| **Users Collection** | `/api/users` |
| **Todos for a User** | `/api/users/{userId}/todos` |
| **Collaborators for a Todo** | `/api/users/{userId}/todos/{todoId}/collaborators` |
| **Tasks for a Todo** | `/api/users/{userId}/todos/{todoId}/tasks` |

Each resource supports appropriate HTTP methods:
- `GET` – Retrieve data
- `POST` – Create new entries
- `PUT` – Update existing entries
- `DELETE` – Remove entries

---

## 🔐 Security

This application implements stateless **JWT authentication**:

- Access tokens are required to access all secured endpoints.
- Tokens should be passed in the `Authorization` header using the `Bearer` scheme.
- Endpoints are protected with role-based access:
  - `ADMIN` can access and manage all users
  - `USER` can manage their own todos and tasks

---

## 👥 Predefined Users

These users are preloaded into the database for testing and demonstration purposes:

| Email           | Password | Role  |
|------------------|:--------:|:-----:|
| `mike@mail.com`  | `1111`   | ADMIN |
| `nick@mail.com`  | `2222`   | USER  |
| `nora@mail.com`  | `3333`   | USER  |

Use these credentials to log in and obtain a JWT token.

---

## ⚙️ Technologies Used

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Spring MVC (REST Controllers)
- H2 / PostgreSQL (DB configurable)
- Maven or Gradle

---

## 📂 Project Structure
src
├── main
│ ├── java
│ │ └── com.example.todolist
│ │ ├── controller # REST controllers
│ │ ├── model # Entity classes
│ │ ├── service # Business logic
│ │ ├── security # JWT filters & config
│ │ └── exception # Custom exception handling
│ └── resources
│ ├── application.yml
│ └── data.sql # Predefined users

## 🚀 How to Run

1. **Clone the repository**

   git clone https://github.com/your-username/todo-api.git
   cd todo-api

2. **Build the project

  ./mvnw clean install
   Run the application

./mvnw spring-boot:run

3. **Access the API

http://localhost:8080/api

🔑 Authentication Flow
Send a POST request to /login with a valid email and password (see predefined users).

Receive a JWT token in the response.

Use this token for all future requests in the Authorization header:

Authorization: Bearer <token>

🧪 Future Improvements
  -  Add refresh tokens

  -  Implement Swagger/OpenAPI documentation

  -  Add pagination and sorting

  -  Support frontend integration (React/Vue)

  -  nhance task scheduling features with due dates and notifications

📬 Contact
If you have questions, ideas, or want to contribute, feel free to open an issue or reach out.


