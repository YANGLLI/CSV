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

  private StudentsDao studentDao;

  /**
   * Default Constructor.
   */
  public ElectivesDao() {
    studentDao = new StudentsDao();
    // it will check the hibernate.cfg.xml file and load it
    // next it goes to all table files in the hibernate file and loads them
    factory = new Configuration()
            .configure().buildSessionFactory();
  }

  public List<Electives> getElectivesByNeuId(String neuId) {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery("from Electives where neuId = :neuId");
      query.setParameter("neuId", neuId);
      return (List<Electives>) query.list();
    } finally {
      session.close();
    }
  }

  public Electives getElectiveById(int electiveId) {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery("from Electives where electiveId = :electiveId");
      query.setParameter("electiveId", electiveId);
      List<Electives> list = query.list();
      if (list.isEmpty()) {
        return null;
      }
      return list.get(0);
    } finally {
      session.close();
    }
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

    if (studentDao.ifNuidExists(elective.getNeuId())) {
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
    } else {
      throw new HibernateException("The student with a given nuid doesn't exists");
    }
    return elective;
  }

  public boolean updateElectives(Electives elective) {
    if (getElectiveById(elective.getElectiveId()) == null) {
      throw new HibernateException("Elective does not exist.");
    }

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

  public boolean deleteElectivesByNeuId(String neuId) {
    Transaction tx = null;

    try {
      session = factory.openSession();
      tx = session.beginTransaction();
      org.hibernate.query.Query query = session.createQuery("DELETE FROM Electives " +
              "WHERE neuId = :neuId ");
      query.setParameter("neuId", neuId);
      query.executeUpdate();
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      throw new HibernateException(e);
    } finally {
      session.close();
    }

    return true;
  }
}
