![GitHub language count](https://img.shields.io/github/languages/count/souzafcharles/rural-geodata-app)
![GitHub top language](https://img.shields.io/github/languages/top/souzafcharles/rural-geodata-app)
![GitHub](https://img.shields.io/github/license/souzafcharles/rural-geodata-app)
![GitHub last commit](https://img.shields.io/github/last-commit/souzafcharles/rural-geodata-app)

# Rural GeoData App - Full Stack Solution

## Project Description

A complete full-stack application for managing rural properties with geographic coordinates. The system provides a modern web interface for registering, viewing, editing, and deleting rural property records with integrated geographic data validation.

### Business Context

- **Geographic Data Management**: Store and manage rural property coordinates;
- **Real-time Validation**: Frontend and backend validation for data integrity;  
- **CRUD Operations**: Complete create, read, update, delete functionality;
- **Responsive Design**: Modern interface accessible on all devices;
- **RESTful API**: Clean separation between frontend and backend.

---

## Technology Stack

### Backend (Java Spring Boot)

| Technology | Version | Description |
|------------|---------|-------------|
| ☕ Java | 17 | Main programming language |
| 🌱 Spring Boot | 3.5.7 | Backend framework |
| 📡 Spring Web | 3.5.7 | REST API and MVC |
| 🗃️ Spring Data JPA | 3.5.7 | Database operations |
| ✅ Spring Validation | 3.5.7 | Input validation |
| 🐘 PostgreSQL | 42.x | Primary database |
| 📄 SpringDoc OpenAPI | 2.5.0 | API documentation |
| 📊 Liquibase | - | Database migrations |
| 📦 Maven | 3.9+ | Dependency management |

### Frontend (Vue.js)

| Technology | Version | Description |
|------------|---------|-------------|
| ⚡ Vue.js | 3.3.0 | Frontend framework |
| 🔷 JavaScript | ES6+ | Programming language |
| 🎨 Materialize CSS | 1.0.0 | CSS framework |
| 📡 Axios | 1.6.0 | HTTP client |
| 🛠️ Vue CLI | 5.0.9 | Build tool |

---

## API Testing with Postman

### Complete Test Collection

A comprehensive Postman collection is included for testing all API endpoints and validation scenarios:

**File:** `RuralProperty-API-Test-Collection.json`

### Test Coverage

| Test Category | Scenarios | Description |
|---------------|-----------|-------------|
| **Health Checks** | ✅ API Status | Verify API is running |
| **GET Operations** | ✅ List All, Get by ID, Error Cases | Complete read operation testing |
| **POST Operations** | ✅ Create with Validation | All constraint validations tested |
| **PUT Operations** | ✅ Update Scenarios | Modify existing properties |
| **DELETE Operations** | ✅ Remove Properties | Delete functionality |
| **Edge Cases** | ✅ Boundaries, Performance | Comprehensive scenario testing |

### Validation Testing

The collection includes tests for all validation rules:
- ✅ **Name Validation**: Required field, max 255 characters
- ✅ **Coordinate Ranges**: Latitude (-90 to 90), Longitude (-180 to 180)
- ✅ **Area Validation**: Positive values required
- ✅ **Error Handling**: Proper HTTP status codes and messages

### How to Use

1. **Import Collection** into Postman
2. **Set Environment Variable**: `baseUrl = http://localhost:8080`
3. **Run Tests**: Execute individual requests or complete test suites
4. **Verify Responses**: Check status codes and validation messages

### Example Test Scenarios

```http
# Valid Creation
POST /api/properties
{
  "name": "Green Valley Farm",
  "latitude": -15.841,
  "longitude": -47.924,
  "areaHectares": 120.5
}

# Validation Error - Invalid Latitude
POST /api/properties  
{
  "name": "Invalid Farm",
  "latitude": 95.0,  # Invalid: > 90
  "longitude": -46.6333,
  "areaHectares": 150.75
}
```

---

## Architecture and Design Patterns

### Backend - Layered Architecture

```
Controller → Service → Repository → Database
```

- **Controller Layer**: REST endpoints, validation, and orchestration;
- **Service Layer**: Business logic and transaction management;
- **Repository Layer**: Data access and persistence;
- **DTO Pattern**: Separation between internal models and API contracts;
- **Centralized Exception Handling**: Consistent error treatment.

### Frontend - Component Architecture

```
App → Services → Components
```

- **Single File Components**: Vue components with template, script, and style;
- **Service Layer**: API communication and business logic;
- **Reactive Data Binding**: Two-way data binding with `v-model`;
- **Event Handling**: Component communication through events.

### Applied Patterns

| Pattern | Application | Benefit |
|---------|-------------|---------|
| **DTO** | `RuralPropertyRequestDTO` | Contract separation |
| **Repository** | `RuralPropertyRepository` | Data abstraction |
| **MVC** | Overall architecture | Concern separation |
| **Exception Handler** | `GlobalExceptionHandler` | Centralized error handling |

---

## Implemented Features

### Backend - Implemented Endpoints

| Endpoint | Method | Description | Status |
|----------|--------|-------------|---------|
| `/api/properties` | POST | Create new rural property | ✅ |
| `/api/properties` | GET | List all rural properties | ✅ |
| `/api/properties/{id}` | GET | Get property by ID | ✅ |
| `/api/properties/{id}` | PUT | Update existing property | ✅ |
| `/api/properties/{id}` | DELETE | Delete property | ✅ |

### Frontend - Features

| Feature | Status | Details |
|---------|--------|---------|
| Property registration form | ✅ Implemented | Create new rural properties |
| Properties listing table | ✅ Implemented | View all registered properties |
| Edit functionality | ✅ Implemented | Update existing properties |
| Delete functionality | ✅ Implemented | Remove properties |
| View details | ✅ Implemented | Property information modal |
| Frontend validation | ✅ Implemented | Mirroring backend constraints |
| Responsive design | ✅ Implemented | Materialize CSS framework |
| Error handling | ✅ Implemented | User-friendly messages |
| Green theme | ✅ Implemented | Agriculture-inspired design |

---

## Technical Decisions and Trade-offs

### Backend - Architectural Decisions

| Decision | Justification | Trade-off |
|----------|---------------|-----------|
| **DTO Pattern** | Clear API contract separation | Additional classes |
| **Spring Validation** | Standardized validation framework | Configuration complexity |
| **Liquibase Migrations** | Versioned database changes | Learning curve |
| **OpenAPI Documentation** | Automatic API documentation | Additional dependency |

### Frontend - UX/UI Decisions

| Decision | Justification | Benefit |
|----------|---------------|---------|
| **Vue.js with Options API** | Simplicity for junior developers | Less reactive than Composition API |
| **Materialize CSS** | Consistent material design | Less customization flexibility |
| **Axios for HTTP** | Promise-based HTTP client | Additional dependency |
| **Green color scheme** | Agriculture thematic | Professional appearance |

---

## Validation Strategy

### Backend Validation (Spring Boot)

```java
// Entity validation matching frontend
@NotBlank(message = "Property name is required")
@Size(max = 255, message = "Property name must not exceed 255 characters")
private String name;

@DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
@DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
private Double latitude;

@DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")  
@DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
private Double longitude;

@Positive(message = "Area must be positive")
private Double areaHectares;
```

### Frontend Validation (Vue.js)

- ✅ **Name**: Required, max 255 characters;
- ✅ **Latitude**: Optional, between -90 and 90;
- ✅ **Longitude**: Optional, between -180 and 180; 
- ✅ **Area**: Required, positive value;
- ✅ **Contextual error display**: Errors shown only when relevant.

---

## How to Run the Project

### Prerequisites

- Java 17+
- Node.js 14+
- Vue CLI 5+
- Maven 3.6+
- PostgreSQL 12+

### 1. Backend (Spring Boot)

```bash
# Navigate to backend directory
cd rural-geodata-api

# Configure database connection in .env file
# DATABASE_URL=jdbc:postgresql://localhost:5432/ruralgeodata
# DATABASE_USERNAME=postgres  
# DATABASE_PASSWORD=yourpassword

# Compile and run
mvn clean spring-boot:run

# Application available at: http://localhost:8080
# API Documentation: http://localhost:8080/swagger-ui.html
```

### 2. Frontend (Vue.js)

```bash
# Navigate to frontend directory
cd rural-geodata-web

# Install dependencies
npm install

# Run in development mode
npm run serve

# Application available at: http://localhost:8081
```

### 3. Database Setup

```sql
-- Database will be automatically created and populated
-- via Liquibase migrations with sample data
```

### 4. API Testing (Postman)

```bash
# Import the Postman collection
File → Import → Select 'RuralProperty-API-Test-Collection.json'

# Set environment variable
baseUrl = http://localhost:8080

# Run test suites to verify API functionality
```

---

## Usage Examples

### Backend - API Examples

#### 1. Create Property
```http
POST http://localhost:8080/api/properties
Content-Type: application/json

{
  "name": "Green Valley Farm",
  "latitude": -15.841,
  "longitude": -47.924, 
  "areaHectares": 120.5
}
```

#### 2. List All Properties
```http
GET http://localhost:8080/api/properties
```

#### 3. Update Property
```http
PUT http://localhost:8080/api/properties/1
Content-Type: application/json

{
  "name": "Green Valley Farm Updated",
  "latitude": -15.842,
  "longitude": -47.925,
  "areaHectares": 125.0
}
```

### Frontend - Usage Flow

1. **Initial Access**: System loads existing properties in table;
2. **Create New**: Fill form and click "Save" to register property;
3. **Edit Existing**: Click edit button, modify data, click "Update";
4. **View Details**: Click view button to see property information;
5. **Delete**: Click delete button with confirmation.

### Implemented Validations

- ✅ Property name required (max 255 characters);
- ✅ Latitude between -90 and 90 (optional);
- ✅ Longitude between -180 and 180 (optional);
- ✅ Area must be positive (required);
- ✅ Real-time validation feedback;
- ✅ Backend validation as security layer.

---

## Conclusion

### Requirements Met

| Requirement | Status | Observation |
|-------------|--------|-------------|
| Backend CRUD API | ✅ Complete | All endpoints implemented |
| Frontend Interface | ✅ Complete | Vue.js with Materialize CSS |
| Data Validation | ✅ Complete | Both frontend and backend |
| Geographic Data | ✅ Complete | Coordinate storage and validation |
| Responsive Design | ✅ Complete | Mobile-friendly interface |
| Error Handling | ✅ Complete | User-friendly messages |
| API Testing | ✅ Complete | Postman collection included |

### Project Highlights

- **Full-Stack Integration**: Seamless frontend-backend communication;
- **Validation Consistency**: Same rules applied on both layers;
- **Modern Stack**: Current versions of Spring Boot and Vue.js;
- **Production Ready**: Proper error handling and validation;
- **Comprehensive Testing**: Complete Postman test collection;
- **Documentation**: Complete API documentation with OpenAPI.

## References

Build & Run. (n.d.). *Investment Aggregator* [Video playlist]. YouTube. https://www.youtube.com/watch?v=Tnl4YnB6E54&list=PLxCh3SsamNs62j6T7bv6f1_1j9H9pEzkC&ab_channel=Build%26Run

Samuelson Brito. (n.d.). *VueJS - Consuming a REST API* [Video playlist]. YouTube. https://www.youtube.com/playlist?list=PLWd_VnthxxLeRdaga093nbR64dALpbPwI

Wellington de Oliveira. (n.d.). *Spring Boot and Angular* [Video playlist]. YouTube. https://www.youtube.com/watch?v=eTQmjY4oO_s&list=PLtII2Mw41oA0LLJgLVeyRWO5IPUtdfRth&index=15&ab_channel=WellingtondeOliveira