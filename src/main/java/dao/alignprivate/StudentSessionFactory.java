package dao.alignprivate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class StudentSessionFactory {
  private static SessionFactory factory;

  static {
    factory = new Configuration()
            .configure("/hibernate.Private.cfg.xml").buildSessionFactory();
  }

  public static SessionFactory getFactory() {
    return factory;
  }
}
