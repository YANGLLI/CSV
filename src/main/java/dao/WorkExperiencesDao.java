package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import enums.Campus;
import model.WorkExperiences;

import javax.persistence.TypedQuery;
import java.util.List;

public class WorkExperiencesDao {
  private SessionFactory factory;
  private Session session;

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
    session = factory.openSession();
    Transaction tx = null;
    try {
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
   * Check if a specific work experience in database based on id.
   *
   * @param workExperienceId Work Experience Id
   * @return true if existed, false if not.
   */
  public boolean ifExperienceidExists(String workExperienceId) {
    boolean find = false;

    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery("FROM WorkExperiences WHERE workExperienceId = :workExperienceId");
      query.setParameter("workExperienceId", workExperienceId);
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
