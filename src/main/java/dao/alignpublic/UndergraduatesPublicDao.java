package dao.alignpublic;

import model.alignpublic.UndergraduatesPublic;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UndergraduatesPublicDao {

  private SessionFactory factory;
  private Session session;

  public SessionFactory getFactory() {
    return factory;
  }

  public Session getSession() {
    return session;
  }

  public UndergraduatesPublicDao(boolean test) {
    if (test) {
      this.factory = PublicTestSessionFactory.getFactory();
    } else {
      this.factory = PublicSessionFactory.getFactory();
    }
  }

  public UndergraduatesPublic createUndergraduate(UndergraduatesPublic undergraduate) {
    Transaction tx = null;
    try {
      session = factory.openSession();
      tx = session.beginTransaction();
      session.save(undergraduate);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      throw new HibernateException(e);
    } finally {
      session.close();
    }
    return undergraduate;
  }
}
