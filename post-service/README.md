# Post Service

Service for managing user posts in the Social Media Backend.

**Port:** 8084

## Endpoints

### Create Post
- **Endpoint:** `POST /posts`
- **Description:** Create a new post
- **Headers:**
  - `X-User-Id` (Long): ID of the post author
  - `X-Username` (String): Username of the post author
- **Request Body:**
  ```json
  {
    "content": "string (max 1000 characters)"
  }
  ```
- **Response:**
  ```json
  {
    "id": "long",
    "authorId": "long",
    "authorUsername": "string",
    "content": "string",
    "createdAt": "datetime",
    "updatedAt": "datetime"
  }
  ```

### Get Post by ID
- **Endpoint:** `GET /posts/{postId}`
- **Description:** Retrieve a specific post by ID
- **Path Parameters:**
  - `postId` (Long): ID of the post
- **Response:** Post object (see Create Post response)

### Update Post
- **Endpoint:** `PUT /posts/{postId}`
- **Description:** Update an existing post (author only)
- **Headers:**
  - `X-User-Id` (Long): ID of the requester
- **Path Parameters:**
  - `postId` (Long): ID of the post
- **Request Body:**
  ```json
  {
    "content": "string (max 1000 characters)"
  }
  ```
- **Response:** Updated post object

### Delete Post
- **Endpoint:** `DELETE /posts/{postId}`
- **Description:** Delete a post (author only)
- **Headers:**
  - `X-User-Id` (Long): ID of the requester
- **Path Parameters:**
  - `postId` (Long): ID of the post

### Get Posts by User
- **Endpoint:** `GET /posts/users/{userId}`
- **Description:** Retrieve paginated posts by a specific user
- **Path Parameters:**
  - `userId` (Long): ID of the user
- **Query Parameters:**
  - `page` (int, default: 0): Page number
  - `size` (int, default: 10): Number of posts per page
- **Response:**
  ```json
  {
    "content": [Post objects],
    "totalElements": "long",
    "totalPages": "int",
    "currentPage": "int"
  }
  ```

## Access via API Gateway

All endpoints can be accessed through the API Gateway at `http://localhost:8081/posts/**`

## Database

- **Database Name:** `auth_db`
- **Host:** `localhost:3306`
- **Username:** `root`
- **Password:** `7070`

## Service Registration

This service is registered with Eureka Service Registry at `http://localhost:8761/eureka/`

