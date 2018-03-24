package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import enums.Campus;
import model.Electives;

import java.util.List;

public class ElectivesDao {
  private SessionFactory factory;
  private Session session;

  /**
   * Default Constructor.
   */
  public ElectivesDao() {
    // it will check the hibernate.cfg.xml file and load it
    // next it goes to all table files in the hibernate file and loads them
    factory = new Configuration()
            .configure().buildSessionFactory();
  }

  /**
   * This is the function to add an Elective for a given student into database.
   *
   * @param elective elective to be added; not null.
   * @return true if insert successfully. Otherwise throws exception.
   */
  public Electives addElective(Electives elective) {
    if (elective == null) {
      return null;
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

  public boolean updateElectives(Electives elective) {
    session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      session.saveOrUpdate(elective);
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
   * Check if a specific elective in database based on id.
   *
   * @param electiveId Elective Id
   * @return true if existed, false if not.
   */
  public boolean ifElectiveidExists(String electiveId) {
    boolean find = false;

    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery("FROM Electives WHERE electiveId = :electiveId");
      query.setParameter("electiveId", electiveId);
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
