# Follow Service

Service for managing user follow relationships in the Social Media Backend.

**Port:** 8087

## Endpoints

### Follow User
- **Endpoint:** `POST /follows/{userId}`
- **Description:** Follow a user
- **Headers:**
  - `X-User-Id` (Long): ID of the follower
- **Path Parameters:**
  - `userId` (Long): ID of the user to follow

### Unfollow User
- **Endpoint:** `DELETE /follows/{userId}`
- **Description:** Unfollow a user
- **Headers:**
  - `X-User-Id` (Long): ID of the follower
- **Path Parameters:**
  - `userId` (Long): ID of the user to unfollow

### Check Following Status
- **Endpoint:** `GET /follows/{userId}/status`
- **Description:** Check if the current user is following a specific user
- **Headers:**
  - `X-User-Id` (Long): ID of the follower
- **Path Parameters:**
  - `userId` (Long): ID of the user to check
- **Response:** `boolean` (true if following, false otherwise)

### Get Follower Count
- **Endpoint:** `GET /follows/{userId}/followers/count`
- **Description:** Get the total number of followers for a user
- **Path Parameters:**
  - `userId` (Long): ID of the user
- **Response:** `long` (follower count)

### Get Following Count
- **Endpoint:** `GET /follows/{userId}/following/count`
- **Description:** Get the total number of users a user is following
- **Path Parameters:**
  - `userId` (Long): ID of the user
- **Response:** `long` (following count)

### Get Following List
- **Endpoint:** `GET /follows/following/{userId}`
- **Description:** Get list of user IDs that a user is following
- **Path Parameters:**
  - `userId` (Long): ID of the user
- **Response:** Array of user IDs (Long[])

## Access via API Gateway

All endpoints can be accessed through the API Gateway at `http://localhost:8081/follows/**`

## Database

- **Database Name:** `follow_db`
- **Host:** `localhost:3306`
- **Username:** `root`
- **Password:** `7070`

## Service Registration

This service is registered with Eureka Service Registry at `http://localhost:8761/eureka/`

