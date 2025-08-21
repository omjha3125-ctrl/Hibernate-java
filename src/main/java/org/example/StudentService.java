package org.example;

import entities.Student;
import entities.Certificates;
import org.example.Util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentService {
    private SessionFactory sessionFactory = Util.getSessionFactory();
    
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
    
    public Student getStudentById(Long studentId) {
        // Use try-with-resources to ensure session is closed automatically
        try (Session session = sessionFactory.openSession()) {
            // get() returns the entity or null if not found
            return session.get(Student.class, studentId);
        }
    }
    
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
    
    public void saveCertificate(Certificates certificate) {
        // Use try-with-resources to ensure session is closed automatically
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(certificate);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e; // Re-throw the exception
            }
        }
    }
    
    public Certificates getCertificateById(Long certificateId) {
        // Use try-with-resources to ensure session is closed automatically
        try (Session session = sessionFactory.openSession()) {
            // get() returns the entity or null if not found
            return session.get(Certificates.class, certificateId);
        }
    }
    
    public void updateCertificate(Certificates certificate) {
        // Use try-with-resources to ensure session is closed automatically
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                // merge() updates the entity or inserts it if it doesn't exist
                session.merge(certificate);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e; // Re-throw the exception
            }
        }
    }
    
    public void deleteCertificate(Long certificateId) {
        // Use try-with-resources to ensure session is closed automatically
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                // First, fetch the certificate to delete
                Certificates certificate = session.get(Certificates.class, certificateId);
                
                // If certificate exists, delete it
                if (certificate != null) {
                    session.remove(certificate);
                    System.out.println("Certificate with ID " + certificateId + " deleted successfully.");
                } else {
                    System.out.println("Certificate with ID " + certificateId + " not found.");
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
}