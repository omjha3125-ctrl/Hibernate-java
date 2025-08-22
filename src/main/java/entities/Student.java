package entities;

// Import statements for Java Persistence API (JPA) annotations used by Hibernate.
// These annotations tell Hibernate how to map this Java class to a database table.
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The Student class is a Plain Old Java Object (POJO) that represents a student entity.
 * 
 * When we use Hibernate (an ORM - Object-Relational Mapping tool), we need to tell it
 * how this Java object relates to a database table. The annotations below do this.
 * 
 * Key Concepts Demonstrated:
 * - Entity Mapping: @Entity, @Table
 * - Primary Key: @Id, @GeneratedValue
 * - Field Mapping: Direct mapping of fields to columns
 * - Relationships: One-to-Many relationship with Certificates (@OneToMany)
 * - Lazy Loading: Fetching related data only when needed
 * - Cascading: Automatically applying operations to related entities
 * - Orphan Removal: Automatically deleting child entities when removed from the parent
 */
@Entity // This annotation marks the class as a Hibernate entity, meaning it's mapped to a database table.
@Table(name = "stud") // This specifies the name of the database table that this entity maps to.
public class Student {

    /**
     * The unique identifier for a Student record.
     * 
     * In databases, each row in a table typically has a unique ID. This field corresponds to that.
     * 
     * Annotations Used:
     * - @Id: Marks this field as the Primary Key of the database table.
     * - @GeneratedValue(strategy = GenerationType.IDENTITY): Tells Hibernate that the database 
     *   should automatically generate this value (e.g., auto-increment in PostgreSQL).
     */
    @Id // Marks this field as the Primary Key of the database table.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tells Hibernate that the database should automatically generate this value (e.g., auto-increment in PostgreSQL).
    private long studentId; // The actual ID field. 'private' means it's only accessible within this class directly.

    /**
     * The name of the student.
     * 
     * This field will be mapped to a column in the 'stud' table.
     * The column name will typically match the field name ('name') unless specified otherwise.
     */
    private String name; // Stores the student's name.

    /**
     * The college the student is attending.
     * 
     * Mapped to a column in the 'stud' table.
     */
    private String college; // Stores the student's college.

    /**
     * The student's phone number.
     * 
     * Mapped to a column in the 'stud' table.
     */
    private String phone; // Stores the student's phone number.

    /**
     * Indicates whether the student's record is active.
     * 
     * This field has a default value of true, meaning a new student is considered active by default.
     * Mapped to a column in the 'stud' table.
     */
    private boolean isActive = true; // Stores the student's active status.

    /**
     * The list of certificates belonging to this student.
     * 
     * This field establishes the one-to-many relationship between Student and Certificates.
     * One student can have many certificates.

     * Annotations Used:
     * - @OneToMany: Defines the one-to-many relationship with the Certificates entity.
     * - mappedBy = "student": Indicates that the "student" field in the Certificates entity owns the relationship.
     * - fetch = FetchType.LAZY: Certificates will be loaded only when explicitly accessed (lazy loading).
     * - cascade = CascadeType.ALL: All operations (persist, merge, remove, etc.) will cascade to certificates.
     * - orphanRemoval = true: If a certificate is removed from this list, it will be deleted from the database.
     */
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certificates> certificates = new ArrayList<>();

    /**
     * Default constructor.
     * 
     * Hibernate requires a no-argument constructor to create instances of entities using reflection.
     * It's good practice to have one, even if implicit.
     */
    public Student() {
    }

    // --- Getter and Setter methods ---
    // These methods allow controlled access and modification of the private fields from outside the class.
    // They are standard practice in Java beans and are used by Hibernate and other frameworks.

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Certificates> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificates> certificates) {
        this.certificates = certificates;
    }
}