# Spring User Data Service (Production Edition)

An enterprise-grade, production-ready Spring Boot REST API for managing user profiles with PostgreSQL.

## 🌟 Production Features
- **API Versioning**: Industry-standard `/api/v1` versioning logic.
- **Strict Validation**: Bean Validation (JSR-380) for data integrity.
- **DTO Architecture**: Decoupled Request/Response models to protect data structures.
- **Unified Exception Handling**: Centralized JSON error responses.
- **Interactive Documentation**: Built-in Swagger UI and OpenAPI documentation.
- **Production Logging**: SLF4J/Logback integration for monitoring.
- **SaaS Components**: Pagination, Sorting, Search, and Analytics.

## 📖 API Documentation (v1)

Access the interactive documentation via Swagger UI:
`http://localhost:8080/swagger-ui/index.html`

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/v1/users` | Paginated & sorted users |
| `GET` | `/api/v1/users/{id}` | Find user by ID |
| `GET` | `/api/v1/users/search`| Search by name |
| `GET` | `/api/v1/users/stats` | Analytics summary |
| `POST` | `/api/v1/users` | Create user (Validated) |
| `PUT` | `/api/v1/users/{id}` | Update user (Validated) |
| `PATCH`| `/api/v1/users/{id}/status`| Toggle status |
| `DELETE`| `/api/v1/users/{id}` | Secure delete |

## ⚙️ Setup
1. Configure `application.properties`.
2. Run as Spring Boot app.
3. Test via Swagger UI or PowerShell.
