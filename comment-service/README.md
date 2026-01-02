# Comment Service

Service for managing comments on posts in the Social Media Backend.

**Port:** 8085

## Endpoints

### Create Comment
- **Endpoint:** `POST /comments/posts/{postId}`
- **Description:** Create a new comment on a post
- **Headers:**
  - `X-User-Id` (Long): ID of the comment author
  - `X-Username` (String): Username of the comment author
- **Path Parameters:**
  - `postId` (Long): ID of the post
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
    "postId": "long",
    "authorId": "long",
    "authorUsername": "string",
    "content": "string",
    "createdAt": "datetime",
    "updatedAt": "datetime"
  }
  ```

### Get Comments by Post
- **Endpoint:** `GET /comments/posts/{postId}`
- **Description:** Retrieve all comments for a specific post
- **Path Parameters:**
  - `postId` (Long): ID of the post
- **Response:** Array of comment objects

### Update Comment
- **Endpoint:** `PUT /comments/{commentId}`
- **Description:** Update an existing comment (author only)
- **Headers:**
  - `X-User-Id` (Long): ID of the requester
- **Path Parameters:**
  - `commentId` (Long): ID of the comment
- **Request Body:**
  ```json
  {
    "content": "string (max 1000 characters)"
  }
  ```
- **Response:** Updated comment object

### Delete Comment
- **Endpoint:** `DELETE /comments/{commentId}`
- **Description:** Delete a comment (author only)
- **Headers:**
  - `X-User-Id` (Long): ID of the requester
- **Path Parameters:**
  - `commentId` (Long): ID of the comment

### Get Comment Count
- **Endpoint:** `GET /comments/posts/{postId}/count`
- **Description:** Get the total number of comments on a post
- **Path Parameters:**
  - `postId` (Long): ID of the post
- **Response:** `long` (comment count)

## Access via API Gateway

All endpoints can be accessed through the API Gateway at `http://localhost:8081/comments/**`

## Database

- **Database Name:** `comment_db`
- **Host:** `localhost:3306`
- **Username:** `root`
- **Password:** `7070`

## Service Registration

This service is registered with Eureka Service Registry at `http://localhost:8761/eureka/`

