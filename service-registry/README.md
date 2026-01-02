# Service Registry

Eureka Service Registry for service discovery in the Social Media Backend microservices architecture.

**Port:** 8761

## Overview

The Service Registry is a Netflix Eureka server that enables dynamic service discovery. All microservices register themselves with this registry, allowing the API Gateway and other services to discover and communicate with each other without hardcoding service locations.

## Registered Services

The following microservices register with this Eureka server:

1. **AUTH-SERVICE** (port 8082)
   - Authentication and user registration

2. **USER-SERVICE** (port 8083)
   - User profile management

3. **POST-SERVICE** (port 8084)
   - Post management

4. **COMMENT-SERVICE** (port 8085)
   - Comment management

5. **LIKE-SERVICE** (port 8086)
   - Like management

6. **FOLLOW-SERVICE** (port 8087)
   - Follow relationship management

7. **FEED-SERVICE** (port 8088)
   - Personalized feed generation

8. **API-GATEWAY** (port 8081)
   - API Gateway for routing requests

## Eureka Dashboard

Access the Eureka dashboard at:

```
http://localhost:8761/
```

The dashboard displays:
- All registered service instances
- Service health status
- Instance metadata
- Real-time service availability

## Service Registration Configuration

Each microservice registers with Eureka using the following configuration:

```properties
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.instance.prefer-ip-address=true
```

## Load Balancing

The API Gateway uses Eureka's load balancing capabilities to distribute requests across multiple instances of the same service using the format:

```
lb://SERVICE-NAME
```

Example: `lb://AUTH-SERVICE` routes to the AUTH-SERVICE instance registered in Eureka.

## Health Checks

Eureka performs periodic health checks on all registered services. Services that fail health checks are automatically marked as unavailable and removed from the service registry.

