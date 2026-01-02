# API Gateway

Central entry point for all microservices in the Social Media Backend. Routes requests to appropriate services using Spring Cloud Gateway.

**Port:** 8081

## Service Routes

### Auth Service
- **Route Path:** `/auth/**`
- **Service:** `AUTH-SERVICE` (port 8082)
- **Endpoints:**
  - `POST /auth/register` - Register new user
  - `POST /auth/login` - User login
  - `GET /auth/test` - Test endpoint

### User Service
- **Route Path:** `/users/**`
- **Service:** `USER-SERVICE` (port 8083)
- **Endpoints:**
  - `POST /users/profile` - Create user profile
  - `GET /users/me` - Get authenticated user's profile
  - `PUT /users/me` - Update authenticated user's profile
  - `GET /users/{username}` - Get public profile

### Post Service
- **Route Path:** `/posts/**`
- **Service:** `POST-SERVICE` (port 8084)
- **Endpoints:**
  - `POST /posts` - Create post
  - `GET /posts/{postId}` - Get post by ID
  - `PUT /posts/{postId}` - Update post
  - `DELETE /posts/{postId}` - Delete post
  - `GET /posts/users/{userId}` - Get user's posts (paginated)

### Comment Service
- **Route Path:** `/comments/**`
- **Service:** `COMMENT-SERVICE` (port 8085)
- **Endpoints:**
  - `POST /comments/posts/{postId}` - Create comment
  - `GET /comments/posts/{postId}` - Get post comments
  - `PUT /comments/{commentId}` - Update comment
  - `DELETE /comments/{commentId}` - Delete comment
  - `GET /comments/posts/{postId}/count` - Get comment count

### Like Service
- **Route Path:** `/likes/**`
- **Service:** `LIKE-SERVICE` (port 8086)
- **Endpoints:**
  - `POST /likes/posts/{postId}` - Like post
  - `DELETE /likes/posts/{postId}` - Unlike post
  - `GET /likes/posts/{postId}/status` - Check like status
  - `GET /likes/posts/{postId}/count` - Get like count

### Follow Service
- **Route Path:** `/follows/**`
- **Service:** `FOLLOW-SERVICE` (port 8087)
- **Endpoints:**
  - `POST /follows/{userId}` - Follow user
  - `DELETE /follows/{userId}` - Unfollow user
  - `GET /follows/{userId}/status` - Check follow status
  - `GET /follows/{userId}/followers/count` - Get follower count
  - `GET /follows/{userId}/following/count` - Get following count
  - `GET /follows/following/{userId}` - Get following list

### Feed Service
- **Route Path:** `/feed/**`
- **Service:** `FEED-SERVICE` (port 8088)
- **Endpoints:**
  - `GET /feed` - Get personalized user feed

## Service Discovery

The API Gateway uses Eureka Service Registry for dynamic service discovery.

- **Eureka Server:** `http://localhost:8761/eureka/`

## Authentication

JWT tokens are validated at the gateway level for protected endpoints. Include the JWT token in the `Authorization` header:

```
Authorization: Bearer <jwt_token>
```

## Base URL

```
http://localhost:8081
```

All service endpoints should be accessed through this gateway URL.

