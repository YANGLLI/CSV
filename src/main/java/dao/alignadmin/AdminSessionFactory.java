package dao.alignadmin;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AdminSessionFactory {
  private static SessionFactory factory;

  static {
    factory = new Configuration()
            .configure("/hibernate.admin.cfg.xml").buildSessionFactory();
  }

  public static SessionFactory getFactory() {
    return factory;
  }
}
