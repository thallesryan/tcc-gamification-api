# Gamification API

A RESTful API for gamification systems built with Spring Boot, following Clean Architecture principles. This API provides features for managing users, missions, rewards, badges, rankings, and user progress tracking.

## Technologies

- **Java 21**
- **Spring Boot 3.4.0**
- **Spring Security** with OAuth2 Resource Server
- **Keycloak** for authentication and authorization
- **JPA/Hibernate** for persistence
- **MySQL/H2** database
- **MapStruct** for object mapping
- **Lombok** for reducing boilerplate code
- **Swagger/OpenAPI** for API documentation

## Features

### Core Functionalities

- **User Management**: User registration and profile management
- **Platform Management**: Creation of platform that will be used by user
- **Mission System**: Create and manage missions with goals and rules
- **User Missions**: Track user progress through missions and goals
- **Rewards & Badges**: Reward system with badges and rarity levels
- **Rankings**: User rankings by level, goals completed, and mission completion time
- **Progress Tracking**: User progress tracking with levels and points calculation
- **Rarity System**: Rarity-based reward system with configurable points

### Security

- **OAuth2/JWT Authentication** via Keycloak
- **Platform-based Authorization**: Users can only access resources from their authorized platforms
- **Role-based Access Control**: Support for Keycloak roles and permissions

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- Docker (for Keycloak)
- MySQL (optional, H2 is used by default for development)

## Getting Started

### 1. Start Keycloak with Docker

```bash
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:22.0.5 start-dev
```

Keycloak will be available at: `http://localhost:8080`

**Default credentials:**
- Admin Console: `http://localhost:8080`
- Username: `admin`
- Password: `admin`

### 2. Configure Keycloak

1. Access the Keycloak Admin Console
2. Create a new Realm (or use `master`)
3. Create a client with:
   - Client ID: `spring-boot-client` (or your preferred name)
   - Access Type: `public` or `confidential`
   - Valid Redirect URIs: Configure as needed
4. Configure user roles and platform access in the token

### 3. Configure the Application

Update `src/main/resources/application.properties`:

```properties
# Keycloak Configuration
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://127.0.0.1:8080/realms/master

# Database Configuration (H2 for development)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=password
```

### 4. Run the Application

```bash
# Using Maven
./mvnw spring-boot:run

# Or using the wrapper
./mvnw.cmd spring-boot:run  # Windows
```

The API will be available at: `http://localhost:8081`

## API Documentation

Once the application is running, access the Swagger UI at:

- **Swagger UI**: `http://localhost:8081/swagger-ui.html`
- **API Docs**: `http://localhost:8081/api-docs`

## Authentication

All endpoints (except public ones) require a JWT token from Keycloak.

### Getting a Token

```bash
curl -L -X POST 'http://localhost:8081/api/auth/token' \
-H 'Content-Type: application/x-www-form-urlencoded' \
-d 'client_id=your-client-id' \
-d 'client_secret=your-client-secret'
```

### Using the Token

```bash
curl -X GET "http://localhost:8081/api/user/email/user@example.com" \
  -H "Authorization: Bearer YOUR_ACCESS_TOKEN" \
  -H "platform: YourPlatform"
```

## Project Structure

This project follows **Clean Architecture** principles:

- **Domain Layer**: Core business entities and rules
- **Application Layer**: Use cases and business logic orchestration
- **Infrastructure Layer**: External implementations (web, persistence, security)


## Main Endpoints

- `/api/user/` - User management
- `/api/platform/` - Platform management
- `/api/mission/` - Mission management
- `/api/user-mission/` - User mission tracking
- `/api/reward/` - Reward management
- `/api/badge/` - Badge management
- `/api/rarity/` - Rarity configuration
- `/api/ranking/` - Ranking queries

## Configuration

### Database

The application supports both H2 (development). Configure in `application.properties`:

```properties
# H2 (Development)
spring.datasource.url=jdbc:h2:mem:testdb

# MySQL (Production)
# spring.datasource.url=jdbc:mysql://localhost:3306/gamification_db
# spring.datasource.username=root
# spring.datasource.password=your-password
```

### Keycloak

Ensure the `issuer-uri` matches your Keycloak realm configuration:

```properties
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://127.0.0.1:8080/realms/master
```

## Validation

All request bodies are validated using Bean Validation annotations. Validation errors return detailed messages in English.

## Testing

Run tests with:

```bash
./mvnw test
```

---

