package org.example.Util;

// Import statements for Hibernate classes.
import org.hibernate.SessionFactory; // The interface for a factory of sessions.
import org.hibernate.cfg.Configuration; // The class used to configure Hibernate and build a SessionFactory.

/**
 * The Util class is a utility class designed to manage the Hibernate SessionFactory.
 * Creating a SessionFactory is a costly operation, so it's a best practice to create it once
 * and reuse it throughout the application's lifecycle. This class implements the Singleton pattern
 * to ensure only one instance of SessionFactory is created.
 * 
 * Key Concepts Demonstrated:
 * - Singleton Pattern: Ensuring a class has only one instance and providing global access to it
 * - Static Initialization Block: Used for one-time initialization when the class is loaded
 * - Exception Handling: Properly handling configuration errors during initialization
 * - Resource Management: Managing expensive resources like database connection factories
 */
public class Util {

    /**
     * A private static field to hold the single instance of SessionFactory.
     * 'private' means it can only be accessed within this class.
     * 'static' means it belongs to the class itself, not to any specific instance of the class.
     * It's initialized to null and will be created once in the static block.
     * 
     * Purpose:
     * - Holds the single SessionFactory instance for the entire application
     * - Ensures thread-safe access to the SessionFactory
     * - Prevents multiple SessionFactory instances from being created
     */
    private static SessionFactory sessionFactory;

    /**
     * A static initialization block.
     * This block runs once when the class is first loaded by the JVM.
     * It's used here to perform the expensive task of creating the SessionFactory.
     * 
     * Purpose:
     * - Initializes the SessionFactory when the class is first loaded
     * - Handles any exceptions that might occur during initialization
     * - Ensures only one SessionFactory instance is created
     */
    static {
        // Wrap the creation process in a try-catch block to handle potential configuration errors.
        try {
            // Check if the sessionFactory has not been initialized yet.
            // This check is a bit redundant here because the block runs only once,
            // but it's a good practice in Singleton implementations.
            if (sessionFactory == null) {
                /**
                 * --- Steps to create the SessionFactory ---
                 *
                 * 1. new Configuration():
                 *    Creates a new Configuration object. This object is used to specify
                 *    the properties and mappings for a SessionFactory.
                 *
                 * 2. .configure("hibernate.cfg.xml"):
                 *    Tells Hibernate to load its configuration from the specified XML file
                 *    (hibernate.cfg.xml in src/main/resources). This file contains database
                 *    connection details, Hibernate settings (like show_sql, hbm2ddl.auto),
                 *    and entity mappings.
                 *
                 * 3. .buildSessionFactory():
                 *    Uses the configuration information loaded in the previous step to
                 *    build and return an instance of SessionFactory.
                 *    This factory is now ready to create Session objects for database interaction.
                 */
                sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            }
        } catch (Exception e) {
            // If an exception occurs during SessionFactory creation, wrap it in a RuntimeException
            // and throw it. This will cause the application to terminate if the SessionFactory
            // cannot be created, as it's a critical component.
            System.err.println("Error building SessionFactory: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging.
            throw new RuntimeException("Error building SessionFactory: " + e.getMessage(), e);
        }
    }

    /**
     * A public static method to provide access to the single SessionFactory instance.
     * This is the standard way to access the Singleton instance.
     *
     * @return The single, shared SessionFactory instance.
     * 
     * Purpose:
     * - Provides global access to the SessionFactory instance
     * - Ensures that all parts of the application use the same SessionFactory
     * - Maintains the Singleton pattern by returning the single instance
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}