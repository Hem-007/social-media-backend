# User Service

Service for managing user profiles in the Social Media Backend.

**Port:** 8083

## Endpoints

### Create User Profile
- **Endpoint:** `POST /users/profile`
- **Description:** Create a new user profile
- **Headers:**
  - `X-User-Id` (Long): ID of the user
  - `X-Username` (String): Username of the user
  - `X-Email` (String): Email of the user
- **Response:**
  ```json
  {
    "userId": "long",
    "username": "string",
    "email": "string",
    "name": "string",
    "bio": "string",
    "profileImage": "string",
    "coverImage": "string",
    "followersCount": "long",
    "followingCount": "long"
  }
  ```

### Get My Profile
- **Endpoint:** `GET /users/me`
- **Description:** Retrieve the authenticated user's profile
- **Headers:**
  - `X-User-Id` (Long): ID of the user
- **Response:** User profile object (see Create User Profile response)

### Update My Profile
- **Endpoint:** `PUT /users/me`
- **Description:** Update the authenticated user's profile
- **Headers:**
  - `X-User-Id` (Long): ID of the user
- **Request Body:**
  ```json
  {
    "name": "string (max 100 characters)",
    "bio": "string (max 255 characters)",
    "profileImage": "string",
    "coverImage": "string"
  }
  ```
- **Response:** Updated user profile object

### Get Public Profile
- **Endpoint:** `GET /users/{username}`
- **Description:** Retrieve a user's public profile by username
- **Path Parameters:**
  - `username` (String): Username of the user
- **Response:** User profile object

## Access via API Gateway

All endpoints can be accessed through the API Gateway at `http://localhost:8081/users/**`

## Database

- **Database Name:** `user_db`
- **Host:** `localhost:3306`
- **Username:** `root`
- **Password:** `7070`

## Service Registration

This service is registered with Eureka Service Registry at `http://localhost:8761/eureka/`

