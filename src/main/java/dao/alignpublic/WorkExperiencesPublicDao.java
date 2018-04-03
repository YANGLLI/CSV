package dao.alignpublic;

import model.alignpublic.WorkExperiencesPublic;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class WorkExperiencesPublicDao {
  private SessionFactory factory;
  private Session session;

  public SessionFactory getFactory() {
    return factory;
  }

  public Session getSession() {
    return session;
  }

  public WorkExperiencesPublicDao(boolean test) {
    if (test) {
      this.factory = PublicTestSessionFactory.getFactory();
    } else {
      this.factory = PublicSessionFactory.getFactory();
    }
  }

  public WorkExperiencesPublic createWorkExperience(WorkExperiencesPublic workExperience) {
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

}
