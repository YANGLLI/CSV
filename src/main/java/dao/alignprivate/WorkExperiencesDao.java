package dao.alignprivate;

import dao.alignpublic.MultipleValueAggregatedDataDao;
import model.alignpublic.MultipleValueAggregatedData;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import model.alignprivate.WorkExperiences;

import javax.persistence.TypedQuery;
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

  public WorkExperiencesDao(boolean test) {
    if (test) {
      this.factory = StudentTestSessionFactory.getFactory();
    } else {
      this.factory = StudentSessionFactory.getFactory();
    }
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

  //========================
  // AGGREGATED DATA SCRIPTS
  //========================

  // THIS IS THE SCRIPT FOR MACHINE LEARNING
  // How many Align students get jobs?
  public int getTotalStudentsGotJob() {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "SELECT COUNT(DISTINCT we.neuId) FROM WorkExperiences we WHERE we.coop = false ");
      return ((Long) query.list().get(0)).intValue();
    } finally {
      session.close();
    }
  }

  // THIS IS A SCRIPT FOR MACHINE LEARNING / PUBLIC FACING
  // Who are the largest Align student employers?
  public List<MultipleValueAggregatedData> getStudentEmployers() {
    String hql = "SELECT NEW model.alignpublic.MultipleValueAggregatedData ( " +
            "we.companyName, cast(Count(*) as integer) ) " +
            "FROM WorkExperiences we " +
            "WHERE we.coop = false " +
            "GROUP BY we.companyName " +
            "ORDER BY Count(*) DESC ";
    try {
      session = factory.openSession();
      TypedQuery<MultipleValueAggregatedData> query = session.createQuery(hql, MultipleValueAggregatedData.class);
      List<MultipleValueAggregatedData> list = query.getResultList();
      for (MultipleValueAggregatedData data : list) {
        data.setAnalyticTerm(MultipleValueAggregatedDataDao.LIST_OF_EMPLOYERS);
      }
      return list;
    } finally {
      session.close();
    }
  }

}
