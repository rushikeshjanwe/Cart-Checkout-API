# Cart & Checkout API

A complete E-Commerce REST API built with Spring Boot 3, featuring user authentication, product catalog, shopping cart, and order management.

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security + JWT**
- **Spring Data JPA**
- **H2 Database** (in-memory)
- **Swagger/OpenAPI 3**
- **Lombok**
- **Maven**

## Features

- ✅ User Registration & Login (JWT Authentication)
- ✅ Product Catalog (CRUD operations)
- ✅ Shopping Cart Management
- ✅ Order Checkout & History
- ✅ Order Cancellation with Stock Restoration
- ✅ Role-based Access Control (Admin/User)
- ✅ Input Validation
- ✅ Global Exception Handling
- ✅ Swagger API Documentation

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+

### Run the Application

```bash
# Clone/Download the project
cd cart-checkout-api

# Build and run
mvn spring-boot:run
```

### Access Points

| URL | Description |
|-----|-------------|
| http://localhost:8080/swagger-ui.html | Swagger UI |
| http://localhost:8080/h2-console | H2 Database Console |
| http://localhost:8080/api-docs | OpenAPI JSON |

### H2 Console Settings
- JDBC URL: `jdbc:h2:mem:ecommercedb`
- Username: `sa`
- Password: (leave empty)

## Test Credentials

| Role | Email | Password |
|------|-------|----------|
| Admin | admin@test.com | admin123 |
| User | user@test.com | user123 |

## API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/auth/register | Register new user |
| POST | /api/auth/login | Login user |

### Products (Public)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/products | Get all products |
| GET | /api/products/{id} | Get product by ID |
| GET | /api/products/category/{category} | Get by category |

### Products (Admin Only)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/products | Create product |
| PUT | /api/products/{id} | Update product |
| DELETE | /api/products/{id} | Delete product |

### Cart (Authenticated)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/cart | Get cart |
| POST | /api/cart/items | Add to cart |
| PUT | /api/cart/items/{id}?quantity=N | Update quantity |
| DELETE | /api/cart/items/{id} | Remove item |
| DELETE | /api/cart | Clear cart |

### Orders (Authenticated)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/orders/checkout | Checkout cart |
| GET | /api/orders | Order history |
| GET | /api/orders/{id} | Get order |
| POST | /api/orders/{id}/cancel | Cancel order |

## Sample API Calls

### 1. Register User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"john@test.com","password":"password123","fullName":"John Doe"}'
```

### 2. Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"user@test.com","password":"user123"}'
```

### 3. Get Products
```bash
curl http://localhost:8080/api/products
```

### 4. Add to Cart (with token)
```bash
curl -X POST http://localhost:8080/api/cart/items \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{"productId":1,"quantity":2}'
```

### 5. Checkout
```bash
curl -X POST http://localhost:8080/api/orders/checkout \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{"shippingAddress":"123 Main St, City, Country"}'
```

## Project Structure

```
src/main/java/com/ecommerce/
├── CartCheckoutApiApplication.java
├── config/
│   ├── DataLoader.java
│   ├── OpenApiConfig.java
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   ├── CartController.java
│   ├── OrderController.java
│   └── ProductController.java
├── dto/
│   ├── request/
│   └── response/
├── entity/
│   ├── User.java
│   ├── Product.java
│   ├── Cart.java
│   ├── CartItem.java
│   ├── Order.java
│   └── OrderItem.java
├── enums/
├── exception/
├── repository/
├── security/
└── service/
```

## Resume Points

- Built RESTful APIs using **Spring Boot 3** with **JWT authentication**
- Implemented **Spring Security** for role-based access control
- Designed database schema with **JPA/Hibernate** relationships
- Created shopping cart with **transactional operations**
- Developed order management with **stock management** logic
- Added **Swagger/OpenAPI** documentation
- Implemented **global exception handling** with custom exceptions
- Used **Lombok** to reduce boilerplate code

## Author

Your Name - Java Developer

## License

MIT License
