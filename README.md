# Student Certificate Management System

A Java Maven project demonstrating Hibernate ORM with PostgreSQL database. This project manages Student and Certificate entities with CRUD operations, pagination, and query capabilities.

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   ├── entities/
│   │   │   ├── Student.java          # Student entity with one-to-many relationship to certificates
│   │   │   └── Certificates.java     # Certificate entity with many-to-one relationship to student
│   │   └── org/example/
│   │       ├── Main.java             # Entry point demonstrating all functionality
│   │       ├── StudentService.java   # Service layer for student operations
│   │       ├── CertificateService.java # Service layer for certificate operations
│   │       └── Util/
│   │           └── Util.java         # Hibernate SessionFactory management
│   └── resources/
│       └── hibernate.cfg.xml         # Hibernate configuration
```

## Prerequisites
- Java 21
- Maven
- PostgreSQL database

## Setup Instructions

1. **Database Setup**
   - Create a PostgreSQL database named `student_cert_db`
   - Create a user with appropriate permissions to access this database
   - Update the database connection details in `src/main/resources/hibernate.cfg.xml` if needed

2. **Build the Project**
   ```bash
   mvn clean compile
   ```

3. **Run the Application**
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.Main"
   ```

## Key Features

### Student Management
- Create, read, update, and delete students
- Retrieve students with pagination
- Query students by name or college
- Manage student certificates

### Certificate Management
- Create, read, update, and delete certificates
- Associate certificates with students
- Retrieve certificates by student ID

### Hibernate Features
- Entity relationships (one-to-many, many-to-one)
- HQL queries
- Criteria API queries
- Transaction management
- Session management

## Project Components

### Entities
- `Student`: Represents a student with name, college, phone, and certificates
- `Certificates`: Represents a certificate associated with a student

### Services
- `StudentService`: Provides CRUD operations and queries for students
- `CertificateService`: Provides CRUD operations and queries for certificates

### Utilities
- `Util`: Manages Hibernate SessionFactory using Singleton pattern

### Configuration
- `hibernate.cfg.xml`: Configures database connection and Hibernate settings

## Learning Outcomes
This project demonstrates:
1. Hibernate ORM setup and configuration
2. Entity mapping with JPA annotations
3. Entity relationships (one-to-many, many-to-one)
4. Service layer implementation
5. Transaction and session management
6. Different query methods (HQL, Criteria API)
7. Pagination implementation
8. Proper exception handling