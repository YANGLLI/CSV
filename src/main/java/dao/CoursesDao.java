package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import model.Courses;

import java.util.List;

public class CoursesDao {

  private SessionFactory factory;
  private Session session;

  /**
   * Default constructor.
   * it will check the Hibernate.cfg.xml file and load it
   * next it goes to all table files in the hibernate file and loads them.
   */
  public CoursesDao() {
    factory = new Configuration().configure().buildSessionFactory();
  }

  public CoursesDao(boolean test) {
    if (test) {
      factory = new Configuration().configure("/hibernate_private_test.cfg.xml").buildSessionFactory();
    }
  }

  /**
   * Get list of all companies from the private database.
   *
   * @return list of All companies from private database.
   */
  public List<Courses> getAllCourses() {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery("FROM Courses");
      return (List<Courses>) query.list();
    } finally {
      session.close();
    }
  }

  /**
   * Get a course from the private database from their
   * course Id.
   *
   * @param courseId Id that wants to be found.
   * @return Course corresponding to the course Id, null otherwise.
   */
  public Courses getCourseById(String courseId) {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery("FROM Courses WHERE courseId = :courseId");
      query.setParameter("courseId", courseId);
      List listOfCourses = query.list();
      if (listOfCourses.isEmpty()) {
        return null;
      }
      return (Courses) listOfCourses.get(0);
    } finally {
      session.close();
    }
  }

  /**
   * Create a new course in private database
   *
   * @param course course object
   * @return Course if found, null if an error happen.
   */
  public Courses createCourse(Courses course) {
    if (course == null) {
      return null;
    }

    if (getCourseById(course.getCourseId()) != null) {
      throw new HibernateException("Course already exist.");
    }

    session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      session.save(course);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      throw new HibernateException(e);
    } finally {
      session.close();
    }

    return course;
  }

  /**
   * Delete a course from the private database based
   * on the company Id.
   *
   * @param courseId course Id.
   * @return true if course is deleted, false otherwise.
   */
  public boolean deleteCourseById(String courseId) {
    if (courseId == null || courseId.trim().isEmpty()) {
      throw new IllegalArgumentException("Course Id argument cannot be empty / null.");
    }


    Courses course = getCourseById(courseId);
    if (course == null) {
      throw new HibernateException("Course Id cannot be found.");
    }
    session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      session.delete(course);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      throw new HibernateException(e);
    } finally {
      session.close();
    }

    return true;
  }

  /**
   * Update a course based on the provided company model.
   *
   * @param course course object.
   * @return true if course is updated, false otherwise.
   */
  public boolean updateCourse(Courses course) {
    if (getCourseById(course.getCourseId()) != null) {
      session = factory.openSession();
      Transaction tx = null;
      try {
        tx = session.beginTransaction();
        session.saveOrUpdate(course);
        tx.commit();
      } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        throw new HibernateException(e);
      } finally {
        session.close();
      }
    } else {
      throw new HibernateException("Course does not exist.");
    }

    return true;
  }

}