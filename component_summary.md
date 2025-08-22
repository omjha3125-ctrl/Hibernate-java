# Student Certificate Management System - Component Summary

## Project Structure Overview
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

## Key Components

### 1. Entities
#### Student Entity
- **Purpose**: Represents a student in the system
- **Relationships**: One-to-many with Certificates
- **Key Fields**: studentId (PK), name, college, phone, isActive
- **Annotations**: @Entity, @Table, @Id, @GeneratedValue, @OneToMany

#### Certificates Entity
- **Purpose**: Represents a certificate associated with a student
- **Relationships**: Many-to-one with Student
- **Key Fields**: certificate (PK), id, link
- **Annotations**: @Entity, @Table, @Id, @GeneratedValue, @ManyToOne, @JoinColumn

### 2. Configuration
#### hibernate.cfg.xml
- **Purpose**: Configures Hibernate settings and database connection
- **Key Settings**: Database connection properties, dialect, show SQL, hbm2ddl.auto

### 3. Utility
#### Util.java
- **Purpose**: Manages the Hibernate SessionFactory using Singleton pattern
- **Key Methods**: getSessionFactory()

### 4. Services
#### StudentService.java
- **Purpose**: Provides CRUD operations and queries for Student entities
- **Key Methods**:
  - saveStudent(), getStudentById(), updateStudent(), deleteStudent()
  - getAllStudent(), getAllStudentsPaginated()
  - getStudentByName(), getStudentByCriteria()

#### CertificateService.java
- **Purpose**: Provides CRUD operations and queries for Certificate entities
- **Key Methods**:
  - saveCertificate(), getCertificateById(), updateCertificate(), deleteCertificate()
  - getAllCertificates(), getCertificatesByStudentId()

### 5. Main Application
#### Main.java
- **Purpose**: Entry point demonstrating all functionality
- **Key Features**:
  - Creating students with certificates
  - Using StudentService methods
  - Using CertificateService methods
  - Demonstrating pagination
  - Showing different query methods (HQL, Criteria API)

## Key Concepts Demonstrated
1. **Hibernate ORM**: Object-relational mapping between Java objects and database tables
2. **Entity Relationships**: One-to-many relationship between Student and Certificates
3. **Service Layer**: Separation of business logic from data access logic
4. **Transaction Management**: Proper handling of database transactions
5. **Session Management**: Correct usage of Hibernate sessions
6. **Query Methods**: HQL, Criteria API, and parameterized queries
7. **Pagination**: Implementing pagination for large result sets
8. **Exception Handling**: Proper error handling in database operations