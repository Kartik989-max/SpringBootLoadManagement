# Load Management API (Spring Boot & PostgreSQL)

A REST API built with **Spring Boot** and **PostgreSQL** to manage freight loads efficiently. It allows users to **create, retrieve, update, and delete load details**.

## Features  
RESTful API with CRUD operations  
PostgreSQL as database (hosted on **Neon**)  
Spring Data JPA & Hibernate for ORM  
Optimistic locking using **@Version** to handle concurrency  
API documentation provided  

---

##  Functionalities
This API helps users (shippers) to:
- **Add a new load** with details like facility, product type, truck type, and weight.
- **Retrieve all loads** (with filtering options like `shipperId`, `truckType`, etc.).
- **Fetch details of a single load** by its `loadId`.
- **Update load details** (e.g., weight, comment).
- **Delete a load** by its `loadId`.

---

##  Tech Stack
- **Backend:** Spring Boot (Java)
- **Database:** PostgreSQL (Neon DB)
- **ORM:** Hibernate (JPA)
- **Build Tool:** Maven
- **Testing:** Postman, cURL

---

## Project Structure
```
 src/main/java/com/example/demo
 ┣  controller      # Contains REST controllers
 ┣  model           # Entity classes for database
 ┣  repository      # JPA repositories (DAO)
 ┣  service         # Business logic (Service layer)
 ┣  DemoApplication.java  # Main Spring Boot application
 src/main/resources
 ┣  application.properties  # Database & Hibernate config
 test
 ┣  LoadControllerTest.java  # Unit tests for API
 README.md   # Project documentation
```

---

##  Installation & Setup
### 1️ Clone the Repository
```bash
git clone https://github.com/Kartik989-max/SpringBootLoadManagement.git
cd SpringBootLoadManagement
```

### 2️ Configure PostgreSQL Database
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://<your_neon_host>:5432/<database_name>
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3️ Build & Run the Application
```bash
mvn clean install
mvn spring-boot:run
```
The server runs at **http://localhost:8080**.

---

##  API Endpoints

### 1️ Create Load
#### **`POST /load`**
**Request Body:**
```json
{
  "facility": {
    "loadingPoint": "delhi",
    "unloadingPoint": "jaipur",
    "loadingDate": "2025-03-18T10:00:00",
    "unloadingDate": "2025-03-19T18:00:00"
  },
  "productType": "chemicals",
  "truckType": "canter",
  "noOfTrucks": 1,
  "weight": 100.5,
  "comment": "Handle with care",
  "shipperId": "shipper-1234",
  "date": "2025-03-18T08:30:00"
}
 ```

Response (201 Created):

```json
{
  "loadId": "load-5678",
  "facility": {
    "loadingPoint": "delhi",
    "unloadingPoint": "jaipur",
    "loadingDate": "2025-03-18T10:00:00",
    "unloadingDate": "2025-03-19T18:00:00"
  },
  "productType": "chemicals",
  "truckType": "canter",
  "noOfTrucks": 1,
  "weight": 100.5,
  "comment": "Handle with care",
  "shipperId": "shipper-1234",
  "date": "2025-03-18T08:30:00"
}
```
2️ Get All Loads
GET /load
Query Parameters (Optional):

shipperId
truckType
productType
loadingPoint
unloadingPoint
Response (200 OK):

```json
[
  {
    "loadId": "load-5678",
    "productType": "chemicals",
    "truckType": "canter",
    "noOfTrucks": 1,
    "weight": 100.5
  }
]
```
3️ Get Load by ID
GET /load/{loadId}
Response (200 OK):
```json
{
  "loadId": "load-5678",
  "productType": "chemicals",
  "truckType": "canter",
  "noOfTrucks": 1,
  "weight": 100.5
}
```
4️ Update Load
PUT /load/{loadId}
Request Body (Partial Updates Allowed):
```json
{
  "noOfTrucks": 2,
  "weight": 200
}
```
Response (200 OK):
```json
{
  "loadId": "load-5678",
  "productType": "chemicals",
  "truckType": "canter",
  "noOfTrucks": 2,
  "weight": 200
}
```
5️ Delete Load
DELETE /load/{loadId}
Response (204 No Content):
```json
{
  "message": "Load deleted successfully"
}
```
