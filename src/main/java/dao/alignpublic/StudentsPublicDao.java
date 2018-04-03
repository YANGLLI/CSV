package dao.alignpublic;

import model.alignpublic.StudentsPublic;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class StudentsPublicDao {
  private SessionFactory factory;
  private Session session;

  public SessionFactory getFactory() {
    return factory;
  }

  public Session getSession() {
    return session;
  }

  public StudentsPublicDao(boolean test) {
    if (test) {
      this.factory = PublicTestSessionFactory.getFactory();
    } else {
      this.factory = PublicSessionFactory.getFactory();
    }
  }

  public StudentsPublic createStudent(StudentsPublic student) {
    Transaction tx = null;
    if (findStudentByPublicId(student.getPublicId()) != null) {
      throw new HibernateException("Student already exist in public database.");
    } else {
      try {
        session = factory.openSession();
        tx = session.beginTransaction();
        session.save(student);
        tx.commit();
      } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        throw new HibernateException(e);
      } finally {
        session.close();
      }
    }
    return student;
  }

  public StudentsPublic findStudentByPublicId(int publicId) {
    List<StudentsPublic> list;
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery("FROM StudentsPublic WHERE publicId = :publicId ");
      query.setParameter("publicId", publicId);
      list = query.list();
    } finally {
      session.close();
    }
    if (list.isEmpty()) {
      return null;
    }
    return list.get(0);
  }

}
