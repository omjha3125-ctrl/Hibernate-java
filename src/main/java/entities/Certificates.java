package entities;

// Import statements for Java Persistence API (JPA) annotations used by Hibernate.
// These annotations tell Hibernate how to map this Java class to a database table.
import jakarta.persistence.*;

/**
 * The Certificates class is a Plain Old Java Object (POJO) that represents a certificate entity.
 * 
 * This entity demonstrates a many-to-one relationship with the Student entity.
 * Each certificate belongs to one student, but a student can have multiple certificates.
 */
@Entity // This annotation marks the class as a Hibernate entity, meaning it's mapped to a database table.
@Table(name = "st_certificates") // This specifies the name of the database table that this entity maps to.
public class Certificates {

    /**
     * The unique identifier for a Certificate record.
     * 
     * In databases, each row in a table typically has a unique ID. This field corresponds to that.
     */
    @Id // Marks this field as the Primary Key of the database table.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tells Hibernate that the database should automatically generate this value (e.g., auto-increment in PostgreSQL).
    private long certificate; // The actual ID field. 'private' means it's only accessible within this class directly.

    /**
     * The ID of the certificate.
     * 
     * This field will be mapped to a column in the 'st_certificates' table.
     */
    private String id; // Stores the certificate ID.

    /**
     * The link to the certificate.
     * 
     * This field will be mapped to a column in the 'st_certificates' table.
     */
    private String link; // Stores the link to the certificate.

    /**
     * The student who owns this certificate.
     * 
     * This field establishes the many-to-one relationship between Certificate and Student.
     * Many certificates can belong to one student.
     */
    @ManyToOne // This annotation defines the many-to-one relationship with the Student entity.
    @JoinColumn(name = "studenId") // This specifies the foreign key column in the database that links to the Student entity.
    private Student student; // Reference to the Student who owns this certificate.

    /**
     * Default constructor.
     * 
     * Hibernate requires a no-argument constructor to create instances of entities using reflection.
     * It's good practice to have one, even if implicit.
     */
    public Certificates() {
    }

    // --- Getter and Setter methods ---
    // These methods allow controlled access and modification of the private fields from outside the class.
    // They are standard practice in Java beans and are used by Hibernate and other frameworks.

    public long getCertificate() {
        return certificate;
    }

    public void setCertificate(long certificate) {
        this.certificate = certificate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}