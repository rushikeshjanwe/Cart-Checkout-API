# 🚨 Incident Management System

A production-grade, full-stack incident management system built to handle IT infrastructure incidents with real-time notifications, caching, and event-driven architecture.

![Build Status](https://github.com/YOUR_USERNAME/incident-management-system/actions/workflows/build.yml/badge.svg)

---

## 📋 Table of Contents

- [About The Project](#about-the-project)
- [Why I Built This](#why-i-built-this)
- [What I Learned](#what-i-learned)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Features](#features)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Testing Guide](#testing-guide)
- [Project Structure](#project-structure)
- [Future Improvements](#future-improvements)
- [Author](#author)

---

## 🎯 About The Project

This Incident Management System is designed to help IT teams track, manage, and resolve infrastructure incidents efficiently. Think of it like a simplified version of PagerDuty or ServiceNow.

### The Problem It Solves

In any organization, when a server goes down or a service fails, teams need to:
- Know about it immediately (Notifications)
- Track who is working on it (Assignment)
- See the history of what happened (Audit Trail)
- Escalate if not resolved in time (Escalation)

This system provides all of that with a clean API and dashboard.

### Incident Lifecycle
```
┌───────────┐     ┌──────────────┐     ┌──────────┐     ┌────────┐
│ TRIGGERED │ --> │ ACKNOWLEDGED │ --> │ RESOLVED │ --> │ CLOSED │
└───────────┘     └──────────────┘     └──────────┘     └────────┘
```

1. **TRIGGERED:** Incident is created (e.g., "Database server down")
2. **ACKNOWLEDGED:** Someone is looking at it
3. **RESOLVED:** Problem is fixed
4. **CLOSED:** Incident is archived

---

## 💡 Why I Built This

I built this project to:

1. **Learn Microservices Architecture:** Understanding how multiple services communicate
2. **Implement Event-Driven Design:** Using Kafka for real-time notifications
3. **Practice Caching Strategies:** Using Redis to improve API performance
4. **Understand Security:** Implementing JWT authentication from scratch
5. **Learn DevOps:** Docker containerization and CI/CD pipelines
6. **Build a Portfolio Project:** Something real-world to show in interviews

This is not just a CRUD application. It demonstrates:
- How modern backend systems are designed
- How different technologies work together
- How to build scalable, production-ready applications

---

## 📚 What I Learned

### 1. Spring Boot & REST APIs
- Building RESTful APIs with proper HTTP methods (GET, POST, PATCH, PUT, DELETE)
- Request validation using Jakarta Validation
- Global exception handling with @ControllerAdvice
- Pagination and sorting for large datasets

### 2. Database & JPA
- Entity relationships and mappings
- Writing custom queries with @Query annotation
- Database migrations and schema design
- Connection pooling with HikariCP

### 3. Security
- JWT (JSON Web Token) authentication
- Role-based access control (RBAC)
- Password encryption with BCrypt
- Securing REST endpoints

### 4. Caching with Redis
- Cache-aside pattern implementation
- Cache invalidation strategies
- Reduced API response time from 50-100ms to 1-2ms
- Understanding when to cache and when not to

### 5. Event-Driven Architecture with Kafka
- Producer-Consumer pattern
- Asynchronous communication between services
- Event serialization/deserialization
- Handling message failures

### 6. Docker & DevOps
- Writing Dockerfiles for Java applications
- Docker Compose for multi-container orchestration
- Environment variable management
- CI/CD with GitHub Actions

### 7. Frontend with React
- Component-based architecture
- State management with hooks
- API integration with Axios
- Responsive UI design

---

## 🛠️ Tech Stack

| Layer | Technology | Why I Chose It |
|-------|------------|----------------|
| Backend | Java 17, Spring Boot 3.2 | Industry standard, great ecosystem |
| Database | PostgreSQL 15 | Reliable, feature-rich RDBMS |
| Cache | Redis 7 | Fast in-memory caching |
| Message Queue | Apache Kafka | Scalable event streaming |
| Frontend | React 18 | Popular, component-based UI |
| Authentication | JWT | Stateless, scalable auth |
| Documentation | Swagger/OpenAPI | Interactive API docs |
| Containerization | Docker | Consistent environments |
| CI/CD | GitHub Actions | Free, integrated with GitHub |

---

## 🏗️ Architecture
```
                                    ┌─────────────────┐
                                    │   React App     │
                                    │   (Port 3000)   │
                                    └────────┬────────┘
                                             │ HTTP
                                             ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                                                                             │
│                           incident-service (Port 8081)                      │
│                                                                             │
│   ┌─────────────┐    ┌─────────────┐    ┌─────────────┐    ┌────────────┐  │
│   │ Auth        │    │ Incident    │    │ Redis       │    │ Kafka      │  │
│   │ Controller  │    │ Controller  │    │ Cache       │    │ Producer   │  │
│   └─────────────┘    └─────────────┘    └──────┬──────┘    └─────┬──────┘  │
│                                                │                  │         │
└────────────────────────────────────────────────┼──────────────────┼─────────┘
                                                 │                  │
                         ┌───────────────────────┼──────────────────┼─────────┐
                         │                       │                  │         │
                         ▼                       ▼                  ▼         │
                 ┌──────────────┐        ┌─────────────┐    ┌─────────────┐   │
                 │  PostgreSQL  │        │    Redis    │    │    Kafka    │   │
                 │  (Port 5432) │        │ (Port 6379) │    │ (Port 9092) │   │
                 └──────────────┘        └─────────────┘    └──────┬──────┘   │
                                                                   │          │
                         ┌─────────────────────────────────────────┘          │
                         │                                                    │
                         ▼                                                    │
              ┌─────────────────────────────────────────────────────────────┐ │
              │                                                             │ │
              │              notification-service (Port 8082)               │ │
              │                                                             │ │
              │   ┌─────────────┐    ┌─────────────┐    ┌─────────────┐    │ │
              │   │ Kafka       │    │ Email       │    │ SMS         │    │ │
              │   │ Consumer    │───>│ Service     │    │ Service     │    │ │
              │   └─────────────┘    └─────────────┘    └─────────────┘    │ │
              │                             │                  │           │ │
              │                      ┌──────┴──────────────────┘           │ │
              │                      │                                     │ │
              │                      ▼                                     │ │
              │              ┌─────────────┐                               │ │
              │              │ Slack       │                               │ │
              │              │ Service     │                               │ │
              │              └─────────────┘                               │ │
              │                                                             │ │
              └─────────────────────────────────────────────────────────────┘ │
                                                                              │
└─────────────────────────────────────────────────────────────────────────────┘
```

### How It Works

1. **User** accesses React Dashboard or Swagger UI
2. **React/Swagger** sends HTTP requests to incident-service
3. **incident-service** handles business logic:
   - Authenticates user via JWT
   - Checks Redis cache first (fast!)
   - If not in cache, queries PostgreSQL
   - Publishes events to Kafka
4. **notification-service** consumes Kafka events
5. **Notifications** are sent via Email, SMS, Slack

---

## ✨ Features

### Core Features
- ✅ Create, Read, Update incidents
- ✅ Incident status transitions (Acknowledge, Resolve, Close)
- ✅ Severity levels (P1, P2, P3, P4)
- ✅ Incident assignment to users
- ✅ Incident escalation

### Security Features
- ✅ JWT-based authentication
- ✅ Role-based access (ADMIN, USER, VIEWER)
- ✅ Password encryption
- ✅ Protected API endpoints

### Performance Features
- ✅ Redis caching (1-2ms response time)
- ✅ Pagination for large datasets
- ✅ Database indexing

### Notification Features
- ✅ Email notifications (simulated)
- ✅ SMS notifications (simulated)
- ✅ Slack notifications (simulated)
- ✅ Event-driven via Kafka

### DevOps Features
- ✅ Docker containerization
- ✅ Docker Compose orchestration
- ✅ GitHub Actions CI/CD
- ✅ Swagger API documentation

---

## 🚀 Getting Started

### Prerequisites

- Docker Desktop installed and running
- Git installed
- (Optional) Java 17 and Maven for local development

### Option 1: Run with Docker (Recommended)
```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/incident-management-system.git

# Go to project folder
cd incident-management-system

# Start all services
docker-compose up --build
```

**Wait 3-5 minutes for all services to start.**

### Option 2: Run Locally

**Terminal 1 - Start Redis:**
```bash
redis-server
```

**Terminal 2 - Start Kafka:**
```bash
cd C:\kafka
.\bin\windows\kafka-server-start.bat .\config\kraft\server.properties
```

**Terminal 3 - Start incident-service:**
```bash
cd incident-service
mvn spring-boot:run
```

**Terminal 4 - Start notification-service:**
```bash
cd notification-service
mvn spring-boot:run
```

**Terminal 5 - Start React:**
```bash
cd incident-dashboard
npm install
npm start
```

### Access the Application

| Service | URL |
|---------|-----|
| React Dashboard | http://localhost:3000 |
| Swagger API Docs | http://localhost:8081/swagger-ui.html |
| Health Check | http://localhost:8081/actuator/health |

---

## 📖 API Documentation

### Base URL
```
http://localhost:8081/api
```

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
    "username": "admin",
    "email": "admin@test.com",
    "password": "admin123",
    "role": "ADMIN"
}
```

**Response:**
```json
{
    "success": true,
    "data": {
        "token": "eyJhbGciOiJIUzM4NCJ9...",
        "username": "admin",
        "email": "admin@test.com",
        "role": "ADMIN"
    }
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
    "username": "admin",
    "password": "admin123"
}
```

**Response:**
```json
{
    "success": true,
    "data": {
        "token": "eyJhbGciOiJIUzM4NCJ9...",
        "username": "admin",
        "email": "admin@test.com",
        "role": "ADMIN"
    }
}
```

### Incident Endpoints

**Note:** All incident endpoints require authentication. Add this header:
```
Authorization: Bearer <your-token>
```

#### Create Incident
```http
POST /api/incidents
Authorization: Bearer <token>
Content-Type: application/json

{
    "title": "Database server down",
    "description": "Production database not responding to queries",
    "severity": "P1"
}
```

**Response:**
```json
{
    "success": true,
    "data": {
        "id": 1,
        "incidentNumber": "INC-20260113-0001",
        "title": "Database server down",
        "description": "Production database not responding to queries",
        "severity": "P1",
        "status": "TRIGGERED",
        "createdAt": "2026-01-13T10:30:00"
    },
    "message": "Incident created"
}
```

#### Get All Incidents
```http
GET /api/incidents?page=0&size=20
Authorization: Bearer <token>
```

#### Get Incident by ID
```http
GET /api/incidents/1
Authorization: Bearer <token>
```

#### Acknowledge Incident
```http
PATCH /api/incidents/1/acknowledge
Authorization: Bearer <token>
```

#### Resolve Incident
```http
PATCH /api/incidents/1/resolve?resolution=Fixed%20the%20database%20connection
Authorization: Bearer <token>
```

#### Close Incident
```http
PATCH /api/incidents/1/close
Authorization: Bearer <token>
```

#### Escalate Incident
```http
PATCH /api/incidents/1/escalate
Authorization: Bearer <token>
```

#### Assign Incident
```http
PATCH /api/incidents/1/assign?assigneeId=2
Authorization: Bearer <token>
```

---

## 🧪 Testing Guide

### Step-by-Step Testing with Swagger

#### Step 1: Open Swagger UI
```
http://localhost:8081/swagger-ui.html
```

#### Step 2: Register a User
1. Expand **Authentication** section
2. Click **POST /api/auth/register**
3. Click **Try it out**
4. Enter:
```json
{
    "username": "admin",
    "email": "admin@test.com",
    "password": "admin123",
    "role": "ADMIN"
}
```
5. Click **Execute**
6. **Copy the token** from response

#### Step 3: Authorize
1. Click **Authorize** button (top right 🔒)
2. Enter: `Bearer <paste-your-token-here>`
3. Click **Authorize**
4. Click **Close**

#### Step 4: Create an Incident
1. Expand **Incidents** section
2. Click **POST /api/incidents**
3. Click **Try it out**
4. Enter:
```json
{
    "title": "Server Down",
    "description": "Production server not responding",
    "severity": "P1"
}
```
5. Click **Execute**
6. Note the incident ID from response

#### Step 5: Test Incident Lifecycle
1. **Acknowledge:** PATCH /api/incidents/{id}/acknowledge
2. **Resolve:** PATCH /api/incidents/{id}/resolve?resolution=Fixed
3. **Close:** PATCH /api/incidents/{id}/close

#### Step 6: Check Notifications
Look at notification-service logs:
```bash
docker-compose logs notification-service
```

You should see:
```
📥 Received event: CREATED for incident: INC-20260113-0001
📧 EMAIL SENT - To: oncall-team@company.com
📱 SMS SENT - To: +91-9999999999
💬 SLACK MESSAGE SENT - Channel: #incidents
```

### Testing with cURL

#### Register:
```bash
curl -X POST http://localhost:8081/api/auth/register -H "Content-Type: application/json" -d "{\"username\":\"admin\",\"email\":\"admin@test.com\",\"password\":\"admin123\",\"role\":\"ADMIN\"}"
```

#### Login:
```bash
curl -X POST http://localhost:8081/api/auth/login -H "Content-Type: application/json" -d "{\"username\":\"admin\",\"password\":\"admin123\"}"
```

#### Create Incident:
```bash
curl -X POST http://localhost:8081/api/incidents -H "Content-Type: application/json" -H "Authorization: Bearer YOUR_TOKEN" -d "{\"title\":\"Server Down\",\"severity\":\"P1\"}"
```

### Testing with React Dashboard

1. Open http://localhost:3000
2. Login with:
   - Username: `admin`
   - Password: `admin123`
3. Create incidents using the UI
4. Test Acknowledge, Resolve, Close buttons

---

## 📁 Project Structure
```
incident-management-system/
│
├── incident-service/                    # Main backend service
│   ├── src/main/java/com/incident/
│   │   ├── controller/                  # REST API endpoints
│   │   │   ├── AuthController.java
│   │   │   └── IncidentController.java
│   │   ├── service/                     # Business logic
│   │   │   ├── AuthService.java
│   │   │   ├── IncidentService.java
│   │   │   └── IncidentCacheService.java
│   │   ├── entity/                      # Database entities
│   │   │   ├── User.java
│   │   │   └── Incident.java
│   │   ├── repository/                  # Data access layer
│   │   ├── dto/                         # Data transfer objects
│   │   ├── security/                    # JWT authentication
│   │   ├── kafka/                       # Kafka producer
│   │   └── config/                      # Configuration classes
│   ├── src/main/resources/
│   │   └── application.yml              # Application configuration
│   ├── Dockerfile
│   └── pom.xml
│
├── notification-service/                # Kafka consumer service
│   ├── src/main/java/com/incident/
│   │   ├── kafka/                       # Kafka consumer
│   │   └── service/                     # Notification services
│   │       ├── EmailService.java
│   │       ├── SmsService.java
│   │       └── SlackService.java
│   ├── Dockerfile
│   └── pom.xml
│
├── incident-dashboard/                  # React frontend
│   ├── src/
│   │   ├── components/                  # React components
│   │   ├── services/                    # API service
│   │   └── App.js
│   └── package.json
│
├── .github/workflows/                   # CI/CD pipeline
│   └── build.yml
│
├── docker-compose.yml                   # Docker orchestration
└── README.md
```

---

## 🐳 Docker Commands
```bash
# Start all services
docker-compose up --build

# Start in background
docker-compose up -d

# Stop all services
docker-compose down

# View all logs
docker-compose logs

# View specific service logs
docker-compose logs incident-service
docker-compose logs notification-service

# View running containers
docker ps

# Clean up everything
docker-compose down
docker system prune -f
```

---

## 🔧 Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| DB_HOST | PostgreSQL host | localhost |
| DB_USER | Database username | postgres |
| DB_PASSWORD | Database password | postgres |
| REDIS_HOST | Redis host | localhost |
| REDIS_PORT | Redis port | 6379 |
| KAFKA_SERVERS | Kafka bootstrap servers | localhost:9092 |
| JWT_SECRET | Secret key for JWT | (configured in app) |

---

## 🔮 Future Improvements

If I had more time, I would add:

### Technical Improvements
- [ ] **Kubernetes Deployment:** For better scalability and orchestration
- [ ] **API Rate Limiting:** To prevent abuse
- [ ] **Circuit Breaker Pattern:** Using Resilience4j for fault tolerance
- [ ] **Database Read Replicas:** For better read performance
- [ ] **Elasticsearch:** For advanced incident search
- [ ] **Prometheus + Grafana:** For monitoring and alerting

### Feature Improvements
- [ ] **Real Email/SMS Integration:** Using Twilio, SendGrid
- [ ] **Real Slack Integration:** Using Slack API
- [ ] **Incident Comments:** Allow team members to comment
- [ ] **Incident Attachments:** Upload screenshots, logs
- [ ] **SLA Management:** Auto-escalate based on SLA breach
- [ ] **Runbooks:** Link incidents to resolution guides
- [ ] **On-Call Scheduling:** Manage who gets notified when
- [ ] **Incident Analytics:** Charts showing incident trends

### Code Quality
- [ ] **More Unit Tests:** Increase code coverage to 80%+
- [ ] **Integration Tests:** Test full API flows
- [ ] **Load Testing:** Using JMeter or Gatling
- [ ] **Code Quality Gates:** SonarQube integration

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 👨‍💻 Author

**Rushikesh**

- GitHub: [@YOUR_USERNAME](https://github.com/YOUR_USERNAME)
- LinkedIn: [Your LinkedIn](https://linkedin.com/in/YOUR_LINKEDIN)
- Email: your.email@example.com

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## ⭐ Show Your Support

Give a ⭐ if this project helped you!

---

## 🙏 Acknowledgments

- Spring Boot Documentation
- Docker Documentation
- Apache Kafka Documentation
- React Documentation
- All the open-source libraries used in this project
