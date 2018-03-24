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
   * Update a course based on the provided company model.
   *
   * @param course course object.
   * @return true if course is updated, false otherwise.
   */
  public boolean updateCourse(Courses course) {
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

    return true;
  }

  /**
   * Check if a specific course in database based on id.
   *
   * @param courseId Course Id
   * @return true if existed, false if not.
   */
  public boolean ifCourseidExists(String courseId) {
    boolean find = false;

    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery("FROM Courses WHERE courseId = :courseId");
      query.setParameter("courseId", courseId);
      List list = query.list();
      if (!list.isEmpty()) {
        find = true;
      }
    } finally {
      session.close();
    }

    return find;
  }
}
