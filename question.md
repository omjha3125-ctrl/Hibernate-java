# Hibernate Practice Project - Student Certificate Management System

## Project Overview
Create a Java Maven project that uses Hibernate ORM to manage Student and Certificate entities with a PostgreSQL database. The project should demonstrate CRUD operations, entity relationships, pagination, and proper service layer implementation.

## Project Structure
Create the following directory structure:
```
student-certificate-management/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── entities/
│   │   │   │   ├── Student.java
│   │   │   │   └── Certificates.java
│   │   │   └── org/example/
│   │   │       ├── Main.java
│   │   │       ├── StudentService.java
│   │   │       ├── CertificateService.java
│   │   │       └── Util/
│   │   │           └── Util.java
│   │   └── resources/
│   │       └── hibernate.cfg.xml
│   └── test/
│       └── java/
└── target/
```

## Database Setup
1. Create a PostgreSQL database named `student_cert_db`
2. Create a user with appropriate permissions to access this database
3. No need to create tables manually - Hibernate will handle this with `hbm2ddl.auto`

## Entity Requirements

### Student Entity (entities/Student.java)
Create a Student entity with the following specifications:
- Map to database table named "stud"
- Fields:
  - studentId (Long): Primary key with auto-generation
  - name (String)
  - college (String)
  - phone (String)
  - isActive (boolean): Default value true
  - certificates (List<Certificates>): One-to-many relationship with Certificates
- Proper JPA annotations for all fields
- Standard getters and setters
- Default constructor

### Certificates Entity (entities/Certificates.java)
Create a Certificates entity with the following specifications:
- Map to database table named "st_certificates"
- Fields:
  - certificate (Long): Primary key with auto-generation
  - id (String): Certificate ID
  - link (String): Certificate link
  - student (Student): Many-to-one relationship with Student
- Proper JPA annotations for all fields
- Standard getters and setters
- Default constructor

## Hibernate Configuration (resources/hibernate.cfg.xml)
Create a Hibernate configuration file with:
- PostgreSQL database connection properties
- Hibernate dialect for PostgreSQL
- Show SQL queries in console
- Format SQL queries
- Auto-create/update database schema (hbm2ddl.auto)
- Entity mappings for both Student and Certificates

## Utility Class (org/example/Util/Util.java)
Create a Util class that:
- Implements the Singleton pattern for SessionFactory
- Reads configuration from hibernate.cfg.xml
- Provides a static method to get the SessionFactory instance
- Handles proper initialization in a static block

## Service Classes

### StudentService (org/example/StudentService.java)
Implement a StudentService class with the following methods:
1. saveStudent(Student student): Saves a student to the database
2. getStudentById(Long studentId): Retrieves a student by ID
3. updateStudent(Student student): Updates an existing student
4. deleteStudent(Long studentId): Deletes a student by ID
5. getAllStudent(): Retrieves all students
6. getAllStudentsPaginated(int pageNumber, int pageSize): Retrieves students with pagination
7. getStudentByName(String name): Retrieves a student by name using parameterized query
8. getStudentByCriteria(String college): Retrieves students by college using Criteria API

### CertificateService (org/example/CertificateService.java)
Implement a CertificateService class with the following methods:
1. saveCertificate(Certificates certificate): Saves a certificate to the database
2. getCertificateById(Long certificateId): Retrieves a certificate by ID
3. updateCertificate(Certificates certificate): Updates an existing certificate
4. deleteCertificate(Long certificateId): Deletes a certificate by ID
5. getAllCertificates(): Retrieves all certificates
6. getCertificatesByStudentId(Long studentId): Retrieves certificates for a specific student

## Main Application (org/example/Main.java)
Create a main class that demonstrates:
1. Creating and saving students with certificates in a single transaction
2. Using StudentService methods:
   - Retrieve a student by ID
   - Update student information
   - Delete a student
   - Retrieve students with pagination
   - Retrieve students by name
   - Retrieve students by college using Criteria API
3. Using CertificateService methods:
   - Retrieve certificates by student ID
   - Create, update, and delete certificates
   - Retrieve all certificates

## Maven Configuration (pom.xml)
Create a pom.xml file with:
- Java 21 as the target version
- Dependencies:
  - Hibernate Core 7.1.0.Final
  - PostgreSQL JDBC Driver 42.7.7
  - JUnit 4 (test scope)

## Implementation Details

### Transaction Management
- All save, update, and delete operations should be wrapped in transactions
- Proper rollback handling in case of exceptions
- Use try-with-resources for automatic session management

### Exception Handling
- Proper exception handling for database operations
- Re-throw exceptions after rollback when necessary

### Resource Management
- Use try-with-resources for Session objects to ensure they're properly closed
- Proper transaction management with commit/rollback

### Query Methods
- Use HQL for most queries
- Use Criteria API for at least one method
- Use parameterized queries to prevent SQL injection

### Pagination
- Implement pagination in StudentService with proper calculation of first result and max results

### Entity Relationships
- Properly establish one-to-many relationship between Student and Certificates
- Ensure foreign key mapping is correct

## Testing Requirements
The main method should demonstrate:
1. Creating multiple students with certificates
2. Retrieving and displaying student information
3. Updating student information
4. Deleting students
5. Using pagination to retrieve students
6. Working with certificates (create, update, delete)
7. Querying with different methods (by ID, name, college, etc.)

## Expected Output
The program should produce console output showing:
- Successful creation of students and certificates
- Retrieved student information
- Updated student information
- Confirmation of deletions
- Paginated student lists
- Certificate operations