package dao.alignadmin;

import model.alignadmin.ElectivesAdmin;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ElectivesAdminDao {
  private SessionFactory factory;
  private Session session;

  public SessionFactory getFactory() {
    return factory;
  }

  public Session getSession() {
    return session;
  }

  /**
   * Default Constructor.
   */
  public ElectivesAdminDao(boolean test) {
    if (test) {
      this.factory = AdminTestSessionFactory.getFactory();
    } else {
      this.factory = AdminSessionFactory.getFactory();
    }
  }

  /**
   * This is the function to add an Elective for a given student into database.
   *
   * @param elective elective to be added; not null.
   * @return true if insert successfully. Otherwise throws exception.
   */
  public ElectivesAdmin addElective(ElectivesAdmin elective) {
    if (elective == null) {
      throw new IllegalArgumentException("Elective argument cannot be null.");
    }

    Transaction tx = null;
    session = factory.openSession();

    try {
      tx = session.beginTransaction();
      session.save(elective);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      throw new HibernateException(e);
    } finally {
      session.close();
    }

    return elective;
  }
}