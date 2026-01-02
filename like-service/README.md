# Like Service

Service for managing likes on posts in the Social Media Backend.

**Port:** 8086

## Endpoints

### Like Post
- **Endpoint:** `POST /likes/posts/{postId}`
- **Description:** Like a post
- **Headers:**
  - `X-User-Id` (Long): ID of the user liking the post
- **Path Parameters:**
  - `postId` (Long): ID of the post to like

### Unlike Post
- **Endpoint:** `DELETE /likes/posts/{postId}`
- **Description:** Unlike a post
- **Headers:**
  - `X-User-Id` (Long): ID of the user unliking the post
- **Path Parameters:**
  - `postId` (Long): ID of the post to unlike

### Check if Post is Liked
- **Endpoint:** `GET /likes/posts/{postId}/status`
- **Description:** Check if a user has liked a specific post
- **Headers:**
  - `X-User-Id` (Long): ID of the user
- **Path Parameters:**
  - `postId` (Long): ID of the post
- **Response:** `boolean` (true if liked, false otherwise)

### Get Like Count
- **Endpoint:** `GET /likes/posts/{postId}/count`
- **Description:** Get the total number of likes on a post
- **Path Parameters:**
  - `postId` (Long): ID of the post
- **Response:** `long` (like count)

## Access via API Gateway

All endpoints can be accessed through the API Gateway at `http://localhost:8081/likes/**`

## Database

- **Database Name:** `like_db`
- **Host:** `localhost:3306`
- **Username:** `root`
- **Password:** `7070`

## Service Registration

This service is registered with Eureka Service Registry at `http://localhost:8761/eureka/`

