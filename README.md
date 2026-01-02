# Social Media Backend - Microservices Architecture

A comprehensive, scalable microservices-based backend for a social media platform built with Spring Boot, Spring Cloud, and MySQL. This project demonstrates modern distributed system architecture with service discovery, API gateway routing, and inter-service communication.

## 📋 Project Overview

This is a complete backend system for a social media application featuring user authentication, profile management, posts, comments, likes, follows, and personalized feeds. The architecture follows microservices principles with independent services, centralized service discovery, and an API gateway for unified access.

### Key Features

✅ **User Authentication & Authorization** - JWT-based authentication with secure password hashing  
✅ **User Profiles** - Create and manage user profiles with bio, images, and follower counts  
✅ **Posts Management** - Create, read, update, and delete posts with pagination  
✅ **Comments System** - Add, edit, and delete comments on posts  
✅ **Likes Feature** - Like/unlike posts with like count tracking  
✅ **Follow System** - Follow/unfollow users with follower/following counts  
✅ **Personalized Feed** - Get feed of posts from followed users  
✅ **Service Discovery** - Eureka-based dynamic service discovery  
✅ **API Gateway** - Spring Cloud Gateway for unified API access  
✅ **Database Persistence** - MySQL for data storage  

## 🏗️ Architecture Overview

### Microservices Structure

```
┌─────────────────────────────────────────────────────────────┐
│                     API Gateway (8081)                      │
│              Spring Cloud Gateway - Request Router           │
└────────────────────────────────────────────────────────────┘
                              │
        ┌─────────────────────┼─────────────────────┐
        │                     │                     │
        ▼                     ▼                     ▼
┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐
│  Auth Service    │  │  User Service    │  │  Post Service    │
│     (8082)       │  │     (8083)       │  │     (8084)       │
│                  │  │                  │  │                  │
│ • Register       │  │ • Create Profile │  │ • Create Post    │
│ • Login          │  │ • Get Profile    │  │ • Get Post       │
│ • JWT Token      │  │ • Update Profile │  │ • Update Post    │
│ • Test Endpoint  │  │ • Public Profile │  │ • Delete Post    │
└──────────────────┘  └──────────────────┘  └──────────────────┘

        ▼                     ▼                     ▼
┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐
│ Comment Service  │  │  Like Service    │  │ Follow Service   │
│     (8085)       │  │     (8086)       │  │     (8087)       │
│                  │  │                  │  │                  │
│ • Create Comment │  │ • Like Post      │  │ • Follow User    │
│ • Get Comments   │  │ • Unlike Post    │  │ • Unfollow User  │
│ • Update Comment │  │ • Check Status   │  │ • Check Status   │
│ • Delete Comment │  │ • Get Count      │  │ • Get Counts     │
└──────────────────┘  └──────────────────┘  └──────────────────┘

        ▼
┌──────────────────┐
│  Feed Service    │
│     (8088)       │
│                  │
│ • Get User Feed  │
│ • Personalized   │
│   Feed Generation│
└──────────────────┘

        ▼
┌──────────────────────────────────────────┐
│  Service Registry (Eureka) (8761)        │
│  Dynamic Service Discovery & Registration│
└──────────────────────────────────────────┘

        ▼
┌──────────────────────────────────────────┐
│         MySQL Database (3306)            │
│  • auth_db                               │
│  • user_db                               │
│  • post_db                               │
│  • comment_db                            │
│  • like_db                               │
│  • follow_db                             │
│  • feed_db                               │
└──────────────────────────────────────────┘
```

## 🚀 Getting Started

### Prerequisites

- **Java 21** or higher
- **Maven 3.6+**
- **MySQL 8.0+**
- **Git**

### Installation & Setup

#### 1. Clone the Repository
```bash
git clone <repository-url>
cd social-media-backend
```

#### 2. Create MySQL Databases
```sql
CREATE DATABASE auth_db;
CREATE DATABASE user_db;
CREATE DATABASE post_db;
CREATE DATABASE comment_db;
CREATE DATABASE like_db;
CREATE DATABASE follow_db;
CREATE DATABASE feed_db;
```

#### 3. Update Database Credentials
Update the `application.properties` file in each service with your MySQL credentials:
```properties
spring.datasource.username=root
spring.datasource.password=7070
```

#### 4. Build All Services
```bash
# Build Service Registry
cd service-registry
mvn clean package -DskipTests
cd ..

# Build API Gateway
cd api-gateway
mvn clean package -DskipTests
cd ..

# Build Auth Service
cd auth-service
mvn clean package -DskipTests
cd ..

# Build User Service
cd user-service
mvn clean package -DskipTests
cd ..

# Build Post Service
cd post-service
mvn clean package -DskipTests
cd ..

# Build Comment Service
cd comment-service
mvn clean package -DskipTests
cd ..

# Build Like Service
cd like-service
mvn clean package -DskipTests
cd ..

# Build Follow Service
cd follow-service
mvn clean package -DskipTests
cd ..

# Build Feed Service
cd feed-service
mvn clean package -DskipTests
cd ..
```

### Running the Services

Start services in this order:

#### 1. Start Service Registry (Eureka)
```bash
cd service-registry
java -jar target/service-registry-0.0.1-SNAPSHOT.jar
```
Access Eureka Dashboard: `http://localhost:8761/`

#### 2. Start API Gateway
```bash
cd api-gateway
java -jar target/api-gateway-0.0.1-SNAPSHOT.jar
```

#### 3. Start All Microservices
Open separate terminals for each service:

```bash
# Auth Service
cd auth-service
java -jar target/auth-service-0.0.1-SNAPSHOT.jar

# User Service
cd user-service
java -jar target/user-service-0.0.1-SNAPSHOT.jar

# Post Service
cd post-service
java -jar target/post-service-0.0.1-SNAPSHOT.jar

# Comment Service
cd comment-service
java -jar target/comment-service-0.0.1-SNAPSHOT.jar

# Like Service
cd like-service
java -jar target/like-service-0.0.1-SNAPSHOT.jar

# Follow Service
cd follow-service
java -jar target/follow-service-0.0.1-SNAPSHOT.jar

# Feed Service
cd feed-service
java -jar target/feed-service-0.0.1-SNAPSHOT.jar
```

## 📡 API Gateway Routes

All requests go through the API Gateway at `http://localhost:8081`

| Service | Route | Port | Service Name |
|---------|-------|------|--------------|
| Auth | `/auth/**` | 8082 | AUTH-SERVICE |
| Users | `/users/**` | 8083 | USER-SERVICE |
| Posts | `/posts/**` | 8084 | POST-SERVICE |
| Comments | `/comments/**` | 8085 | COMMENT-SERVICE |
| Likes | `/likes/**` | 8086 | LIKE-SERVICE |
| Follows | `/follows/**` | 8087 | FOLLOW-SERVICE |
| Feed | `/feed/**` | 8088 | FEED-SERVICE |

## 🔐 Authentication

The system uses JWT (JSON Web Tokens) for authentication:

1. **Register** - `POST /auth/register` - Create new account
2. **Login** - `POST /auth/login` - Get JWT token
3. **Use Token** - Include in Authorization header: `Authorization: Bearer <token>`

## 📚 API Documentation

Each service has its own detailed README with complete endpoint documentation:

- **[Auth Service](./auth-service/README.md)** - User registration and login
- **[User Service](./user-service/README.md)** - User profile management
- **[Post Service](./post-service/README.md)** - Post CRUD operations
- **[Comment Service](./comment-service/README.md)** - Comment management
- **[Like Service](./like-service/README.md)** - Like functionality
- **[Follow Service](./follow-service/README.md)** - Follow relationships
- **[Feed Service](./feed-service/README.md)** - Personalized feeds
- **[API Gateway](./api-gateway/README.md)** - Gateway routing
- **[Service Registry](./service-registry/README.md)** - Service discovery

## 🗄️ Database Schema

Each service has its own database with independent schema:

- **auth_db** - User credentials and authentication data
- **user_db** - User profiles and metadata
- **post_db** - Posts and post metadata
- **comment_db** - Comments on posts
- **like_db** - Like relationships
- **follow_db** - Follow relationships
- **feed_db** - Feed cache and data

## 🔧 Technology Stack

- **Framework:** Spring Boot 3.3.4 / 3.4.1
- **Cloud:** Spring Cloud 2024.0.2 / 2025.1.0
- **Service Discovery:** Netflix Eureka
- **API Gateway:** Spring Cloud Gateway
- **Authentication:** JWT (jjwt 0.11.5)
- **Database:** MySQL 8.0+
- **ORM:** Spring Data JPA / Hibernate
- **Build Tool:** Maven
- **Java Version:** 21

## 🏃 Quick Start Example

### 1. Register a User
```bash
curl -X POST http://localhost:8081/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "password123"
  }'
```

### 2. Login
```bash
curl -X POST http://localhost:8081/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "password123"
  }'
```

### 3. Create User Profile
```bash
curl -X POST http://localhost:8081/users/profile \
  -H "Content-Type: application/json" \
  -H "X-User-Id: 1" \
  -H "X-Username: john_doe" \
  -H "X-Email: john@example.com"
```

### 4. Create a Post
```bash
curl -X POST http://localhost:8081/posts \
  -H "Content-Type: application/json" \
  -H "X-User-Id: 1" \
  -H "X-Username: john_doe" \
  -d '{
    "content": "Hello, Social Media!"
  }'
```

## 🐛 Troubleshooting

### Service Not Registering with Eureka
- Ensure Service Registry is running on port 8761
- Check `eureka.client.service-url.defaultZone` in each service's `application.properties`
- Verify `@EnableDiscoveryClient` annotation is present in service main class

### API Gateway Not Routing Requests
- Confirm all services are registered in Eureka
- Check gateway routes in `api-gateway/src/main/resources/application.properties`
- Verify service names match exactly (case-sensitive)

### Database Connection Issues
- Ensure MySQL is running on port 3306
- Verify database credentials in `application.properties`
- Check that all required databases are created

### JWT Token Errors
- Ensure JWT secret key matches across services
- Verify token is included in Authorization header
- Check token expiration time

## 📝 Project Structure

```
social-media-backend/
├── api-gateway/              # Spring Cloud Gateway
├── auth-service/             # Authentication & Authorization
├── user-service/             # User Profile Management
├── post-service/             # Post Management
├── comment-service/          # Comment Management
├── like-service/             # Like Management
├── follow-service/           # Follow Management
├── feed-service/             # Feed Generation
├── service-registry/         # Eureka Service Registry
└── README.md                 # This file
```

## 🤝 Contributing

1. Create a feature branch (`git checkout -b feature/AmazingFeature`)
2. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
3. Push to the branch (`git push origin feature/AmazingFeature`)
4. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

Social Media Backend - Microservices Learning Project

## 📞 Support

For issues, questions, or suggestions, please open an issue in the repository.

---

**Last Updated:** January 2, 2026  
**Version:** 1.0.0  
**Status:** Active Development

