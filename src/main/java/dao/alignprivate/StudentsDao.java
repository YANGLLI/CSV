package dao.alignprivate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import model.alignprivate.Students;

import java.util.List;

public class StudentsDao {
  private SessionFactory factory;
  private Session session;

  public SessionFactory getFactory() {
    return factory;
  }

  public Session getSession() {
    return session;
  }

  public StudentsDao(boolean test) {
    if (test) {
      this.factory = StudentTestSessionFactory.getFactory();
    } else {
      this.factory = StudentSessionFactory.getFactory();
    }
  }

  /**
   * This is the function to add a student into database.
   *
   * @param student student to be inserted
   * @return inserted student if successful. Otherwise null.
   */
  public void addStudent(Students student) {
    Transaction tx = null;

    try {
      session = factory.openSession();
      tx = session.beginTransaction();
      session.save(student);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      throw new HibernateException(e);
    } finally {
      session.close();
    }
  }

  /**
   * Update a student record with most recent details.
   *
   * @param student which contains the new student details.
   * @return true if successful. Otherwise, false.
   */
  public boolean updateStudentRecord(Students student) {
    Transaction tx = null;
    String neuId = student.getNeuId();

    try {
      session = factory.openSession();
      tx = session.beginTransaction();
      session.saveOrUpdate(student);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      throw new HibernateException(e);
    } finally {
      session.close();
    }

    return true;
  }

  public int getStudentPublicId(String neuId) {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery("FROM Students WHERE neuId = :studentNuid ");
      query.setParameter("studentNuid", neuId);
      List list = query.list();
      if (list.isEmpty()) {
        return -1;
      }
      Students student = (Students) list.get(0);
      return student.getPublicId();
    } finally {
      session.close();
    }
  }

  /**
   * Check if a specific student existed in database based on neu id.
   *
   * @param neuId Student Neu Id
   * @return true if existed, false if not.
   */
  public boolean ifNuidExists(String neuId) {
    boolean find = false;

    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery("FROM Students WHERE neuId = :studentNeuId");
      query.setParameter("studentNeuId", neuId);
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
