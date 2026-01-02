# Auth Service

Authentication and user registration service for the Social Media Backend.

**Port:** 8082

## Endpoints

### User Registration
- **Endpoint:** `POST /auth/register`
- **Description:** Register a new user account
- **Request Body:**
  ```json
  {
    "username": "string",
    "email": "string",
    "password": "string"
  }
  ```
- **Response:**
  ```json
  {
    "token": "string (JWT token)",
    "message": "string"
  }
  ```

### User Login
- **Endpoint:** `POST /auth/login`
- **Description:** Authenticate user and receive JWT token
- **Request Body:**
  ```json
  {
    "email": "string",
    "password": "string"
  }
  ```
- **Response:**
  ```json
  {
    "token": "string (JWT token)",
    "message": "string"
  }
  ```

### Test Endpoint
- **Endpoint:** `GET /test`
- **Description:** Verify JWT token and access secured endpoint
- **Response:** `"JWT verified — secure endpoint accessed!"`

## Access via API Gateway

All endpoints can be accessed through the API Gateway at `http://localhost:8081`:

- `POST http://localhost:8081/auth/register`
- `POST http://localhost:8081/auth/login`
- `GET http://localhost:8081/auth/test`

## Database

- **Database Name:** `auth_db`
- **Host:** `localhost:3306`
- **Username:** `root`
- **Password:** `7070`

## Service Registration

This service is registered with Eureka Service Registry at `http://localhost:8761/eureka/`

