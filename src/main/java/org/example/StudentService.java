package org.example;

import entities.Student;
import org.example.Util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * The StudentService class provides a layer of abstraction for common database operations
 * related to Student entities. It encapsulates the logic for saving, retrieving, updating,
 * and deleting Student records using Hibernate.
 * 
 * Key Concepts Demonstrated:
 * - Service Layer: Separating business logic from data access logic
 * - Session Management: Properly opening and closing Hibernate sessions
 * - Transaction Management: Handling database transactions with commit and rollback
 * - Exception Handling: Properly handling exceptions during database operations
 * - Resource Management: Using try-with-resources to ensure sessions are closed
 * - HQL (Hibernate Query Language): Using object-oriented queries instead of SQL
 * 
 * Common Points of Confusion Addressed:
 * - HQL vs SQL: HQL uses entity names, not table names
 * - Parameter Binding: Using named parameters to prevent SQL injection
 * - Query Result Methods: uniqueResult() vs list() vs other options
 * - Dynamic Queries: Building queries based on runtime conditions
 */
public class StudentService {
    // Get the SessionFactory instance from our Util class
    private SessionFactory sessionFactory = Util.getSessionFactory();
    
    /**
     * Saves a Student entity to the database.
     * 
     * @param student The Student object to save
     * 
     * Purpose:
     * - Persists a new Student entity to the database
     * - Handles transaction management (commit/rollback)
     * - Ensures proper resource management with try-with-resources
     */
    public void saveStudent(Student student) {
        // Use try-with-resources to ensure session is closed automatically
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(student);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e; // Re-throw the exception
            }
        }
    }
    
    /**
     * Retrieves a Student entity by its ID.
     * 
     * @param studentId The ID of the Student to retrieve
     * @return The Student object if found, null otherwise
     * 
     * Purpose:
     * - Fetches a Student entity from the database by its primary key
     * - Ensures proper resource management with try-with-resources
     */
    public Student getStudentById(Long studentId) {
        // Use try-with-resources to ensure session is closed automatically
        try (Session session = sessionFactory.openSession()) {
            // get() returns the entity or null if not found
            return session.get(Student.class, studentId);
        }
    }
    
    /**
     * Updates an existing Student entity in the database.
     * 
     * @param student The Student object with updated information
     * 
     * Purpose:
     * - Updates an existing Student entity in the database
     * - Handles transaction management (commit/rollback)
     * - Ensures proper resource management with try-with-resources
     */
    public void updateStudent(Student student) {
        // Use try-with-resources to ensure session is closed automatically
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                // merge() updates the entity or inserts it if it doesn't exist
                session.merge(student);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e; // Re-throw the exception
            }
        }
    }
    
    /**
     * Deletes a Student entity from the database by its ID.
     * 
     * @param studentId The ID of the Student to delete
     * 
     * Purpose:
     * - Deletes a Student entity from the database
     * - Handles transaction management (commit/rollback)
     * - Ensures proper resource management with try-with-resources
     */
    public void deleteStudent(Long studentId) {
        // Use try-with-resources to ensure session is closed automatically
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                // First, fetch the student to delete
                Student student = session.get(Student.class, studentId);
                
                // If student exists, delete it
                if (student != null) {
                    session.remove(student);
                    System.out.println("Student with ID " + studentId + " deleted successfully.");
                } else {
                    System.out.println("Student with ID " + studentId + " not found.");
                }
                
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e; // Re-throw the exception
            }
        }
    }

    /**
     * Retrieves all Student entities from the database.
     * 
     * @return A list of all Student objects
     * 
     * Purpose:
     * - Fetches all Student entities from the database
     * - Uses HQL (Hibernate Query Language) for database-independent queries
     * - Ensures proper resource management with try-with-resources
     * 
     * Key Concepts:
     * - HQL (Hibernate Query Language): Database-independent object-oriented queries
     * - Query Object: Used to execute HQL queries
     * - List Return: Returns a list of entities matching the query
     * 
     * Note on HQL vs Table Names:
     * - This query uses "FROM Student" where "Student" is the entity class name
     * - Hibernate maps this to the actual table name ("stud") defined in @Table annotation
     * - If there were multiple tables, you'd need separate entities or native queries
     */
    public List<Student> getAllStudent(){
        try(Session session=sessionFactory.openSession()){
            // HQL query to fetch all Student entities
            // "Student" refers to the entity class, not the database table name
            String getHQL="FROM Student";
            Query<Student> query=session.createQuery(getHQL,Student.class);
            return query.list();
        }
    }

    /**
     * Retrieves a Student entity by name using a parameterized query.
     * 
     * @param name The name of the Student to retrieve
     * @return The Student object if found, null otherwise
     * 
     * Purpose:
     * - Fetches a Student entity from the database by name
     * - Demonstrates parameterized queries and different result methods
     * 
     * Key Concepts Explained:
     * - Parameter Binding: Using :studentname to safely insert values
     * - Why Parameterized Queries: Prevents SQL injection attacks
     * - uniqueResult(): Used when expecting exactly one result
     * - HQL vs SQL: This example shows correct HQL usage
     * 
     * Common Points of Confusion Addressed:
     * - Dynamic Parameters: The :studentname is a named parameter placeholder
     * - Parameter Binding: query.setParameter() replaces :studentname with actual value
     * - uniqueResult(): Returns single result or null (throws exception if multiple found)
     * - Alternatives to uniqueResult(): list(), getSingleResult(), etc.
     */
    public Student getStudentByName(String name){
        try(Session session=sessionFactory.openSession()){
            // HQL query with named parameter
            // :studentname is a placeholder that will be replaced with actual value
            // This is safer than string concatenation as it prevents SQL injection
            String getStHQL="FROM Student WHERE name = :studentname";
            
            // Create query object with HQL string and expected result type
            Query<Student> query=session.createQuery(getStHQL,Student.class);
            
            // Bind the parameter value to the named parameter
            // This safely replaces :studentname with the actual name value
            query.setParameter("studentname", name);
            
            // Execute query and return single result
            // uniqueResult() returns:
            // - The single matching entity if found
            // - null if no matching entity is found
            // - Throws exception if multiple entities match (NonUniqueResultException)
            return query.uniqueResult();
            
            // Alternative methods:
            // query.list() - Returns List<Student> (empty list if none found)
            // query.getSingleResult() - Returns entity or throws exception if not found
        }
    }
}