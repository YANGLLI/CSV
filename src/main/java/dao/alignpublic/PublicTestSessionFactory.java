package dao.alignpublic;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PublicTestSessionFactory {
  private static SessionFactory factory;

  static {
    factory = new Configuration()
            .configure("/hibernate.public.test.cfg.xml").buildSessionFactory();
  }

  public static SessionFactory getFactory() {
    return factory;
  }
}
