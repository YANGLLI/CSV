package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import model.WorkExperiences;

import java.util.Date;
import java.util.List;

public class WorkExperiencesDao {
  private SessionFactory factory;
  private Session session;

  public SessionFactory getFactory() {
    return factory;
  }

  public Session getSession() {
    return session;
  }

  /**
   * Default constructor.
   * it will check the Hibernate.cfg.xml file and load it
   * next it goes to all table files in the hibernate file and loads them.
   */
  public WorkExperiencesDao() {
    factory = new Configuration().configure().buildSessionFactory();
  }

  /**
   * Create a work experience in the private database.
   * This function requires the StudentsPublic object and the Companies
   * object inside the work experience object to be not null.
   *
   * @param workExperience the work experience object to be created; not null.
   * @return newly created WorkExperience if success. Otherwise, return null;
   */
  public WorkExperiences createWorkExperience(WorkExperiences workExperience) {
    Transaction tx = null;
    try {
      session = factory.openSession();
      tx = session.beginTransaction();
      session.save(workExperience);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      throw new HibernateException(e);
    } finally {
      session.close();
    }

    return workExperience;
  }

  /**
   * Update a work experience in the private DB.
   *
   * @param workExperience work experience object; not null.
   * @return true if the work experience is updated, false otherwise.
   */
  public boolean updateWorkExperience(WorkExperiences workExperience) {
    Transaction tx = null;
    try {
      session = factory.openSession();
      tx = session.beginTransaction();
      session.saveOrUpdate(workExperience);
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
   * Check if work experience already exists in database.
   *
   * @param neuId
   * @param companyName
   * @param startDate
   * @return
   */
  public WorkExperiences getWorkExperience(String neuId, String companyName, Date startDate) {
    boolean find = false;
    WorkExperiences experience = null;

    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery("FROM WorkExperiences WHERE neuId = :neuId " +
              "AND companyName = :companyName " +
              "AND startDate = :startDate");
      query.setParameter("neuId", neuId);
      query.setParameter("companyName", companyName);
      query.setParameter("startDate", startDate);
      List list = query.list();
      if (!list.isEmpty()) {
        experience = (WorkExperiences) list.get(0);
      }
    } finally {
      session.close();
    }

    return experience;
  }
}
