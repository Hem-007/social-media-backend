# Feed Service

Service for generating personalized user feeds in the Social Media Backend.

**Port:** 8088

## Endpoints

### Get User Feed
- **Endpoint:** `GET /feed`
- **Description:** Retrieve the personalized feed for the authenticated user (posts from followed users)
- **Headers:**
  - `X-User-Id` (Long): ID of the user
- **Query Parameters:**
  - `page` (int, default: 0): Page number
  - `size` (int, default: 10): Number of posts per page
- **Response:**
  ```json
  {
    "posts": [
      {
        "id": "long",
        "authorId": "long",
        "authorUsername": "string",
        "content": "string",
        "createdAt": "datetime",
        "updatedAt": "datetime"
      }
    ],
    "totalElements": "long",
    "totalPages": "int",
    "currentPage": "int"
  }
  ```

## Access via API Gateway

All endpoints can be accessed through the API Gateway at `http://localhost:8081/feed/**`

## Database

- **Database Name:** `feed_db`
- **Host:** `localhost:3306`
- **Username:** `root`
- **Password:** `7070`

## Service Registration

This service is registered with Eureka Service Registry at `http://localhost:8761/eureka/`

