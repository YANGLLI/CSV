package dao.alignprivate;

import enums.DegreeCandidacy;
import model.alignprivate.PriorEducations;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class PriorEducationsDao {
  private SessionFactory factory;
  private Session session;

  public SessionFactory getFactory() {
    return factory;
  }

  public Session getSession() {
    return session;
  }

  public PriorEducationsDao(boolean test) {
    if (test) {
      this.factory = StudentTestSessionFactory.getFactory();
    } else {
      this.factory = StudentSessionFactory.getFactory();
    }
  }

  /**
   * Create a prior education in the private database.
   * This function requires the Student, institution, major
   * object inside the prior education object to be not null.
   *
   * @param priorEducation the prior education object to be created; not null.
   * @return newly created priorEducation.
   */
  public PriorEducations createPriorEducation(PriorEducations priorEducation) {
    session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      session.save(priorEducation);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      throw new HibernateException(e);
    } finally {
      session.close();
    }

    return priorEducation;
  }

  /**
   * Update a prior education in the private DB.
   *
   * @param priorEducation prior education object; not null.
   * @return true if the prior education is updated, false otherwise.
   */
  public boolean updatePriorEducation(PriorEducations priorEducation) {
    session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      session.saveOrUpdate(priorEducation);
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
   * Get a unique prior education
   *
   * @param neuId
   * @param institutionName
   * @param majorName
   * @return
   */
  public PriorEducations getPriorEducation(String neuId, String institutionName, String majorName, DegreeCandidacy degree) {
    boolean find = false;
    PriorEducations priorEducations = null;

    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery("FROM PriorEducations WHERE neuId = :neuId " +
              "AND institutionName = :institutionName " +
              "AND majorName = :majorName " +
              "AND degreeCandidacy = :degree");
      query.setParameter("neuId", neuId);
      query.setParameter("institutionName", institutionName);
      query.setParameter("majorName", majorName);
      query.setParameter("degree", degree);
      List list = query.list();
      if (!list.isEmpty()) {
        priorEducations = (PriorEducations) list.get(0);
      }
    } finally {
      session.close();
    }

    return priorEducations;
  }
}
