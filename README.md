# Spring User Data Service (SaaS Edition)

A production-ready, SaaS-level Spring Boot REST API for managing user data with PostgreSQL.

## 🚀 SaaS Features
- **Pagination & Sorting**: Efficiently handle large datasets using `Pageable`.
- **Advanced Search**: Case-insensitive name search.
- **Data Analytics**: Analytics endpoint for real-time user statistics.
- **Audit Trails**: Automatic `createdAt` and `updatedAt` timestamps.
- **Status Management**: Lifecycle management via `ACTIVE`/`INACTIVE` statuses.
- **Clean Architecture**: Decoupled Service and Repository layers.

## 📖 API Documentation

| Method | Endpoint | Parameters | Description |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/users` | `page`, `size`, `sortBy`, `direction` | Paginated & sorted list |
| `GET` | `/api/users/{id}` | - | Find user by ID |
| `GET` | `/api/users/search` | `name` | Search users by name |
| `GET` | `/api/users/stats` | - | Get analytics summary |
| `POST` | `/api/users` | Body (JSON) | Create/Update user |
| `PATCH`| `/api/users/{id}/status`| - | Toggle user status |
| `DELETE`| `/api/users/{id}` | - | Permanent removal |

## ⚙️ Setup
1. Configure `application.properties` with your PostgreSQL credentials.
2. The database table will be auto-created/updated via Hibernate.
3. Run as a Spring Boot application.
