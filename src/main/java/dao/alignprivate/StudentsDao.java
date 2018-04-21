package dao.alignprivate;

import dao.alignpublic.MultipleValueAggregatedDataDao;
import enums.Campus;
import enums.EnrollmentStatus;
import model.alignpublic.MultipleValueAggregatedData;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import model.alignprivate.Students;

import javax.persistence.TypedQuery;
import java.util.List;

public class StudentsDao {
  private SessionFactory factory;
  private Session session;

  public SessionFactory getFactory() {
    return factory;
  }

  public Session getSession() {
    return session;
  }

  public StudentsDao(boolean test) {
    if (test) {
      this.factory = StudentTestSessionFactory.getFactory();
    } else {
      this.factory = StudentSessionFactory.getFactory();
    }
  }

  /**
   * This is the function to add a student into database.
   *
   * @param student student to be inserted
   * @return inserted student if successful. Otherwise null.
   */
  public void addStudent(Students student) {
    Transaction tx = null;

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

  /**
   * Update a student record with most recent details.
   *
   * @param student which contains the new student details.
   * @return true if successful. Otherwise, false.
   */
  public boolean updateStudentRecord(Students student) {
    Transaction tx = null;
    String neuId = student.getNeuId();

    try {
      session = factory.openSession();
      tx = session.beginTransaction();
      session.saveOrUpdate(student);
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
   * Get student public id by neu id.
   * @param neuId
   * @return
   */
  public int getStudentPublicId(String neuId) {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery("FROM Students WHERE neuId = :studentNuid ");
      query.setParameter("studentNuid", neuId);
      List list = query.list();
      if (list.isEmpty()) {
        return -1;
      }
      Students student = (Students) list.get(0);
      return student.getPublicId();
    } finally {
      session.close();
    }
  }

  /**
   * Check if a specific student existed in database based on neu id.
   *
   * @param neuId Student Neu Id
   * @return true if existed, false if not.
   */
  public boolean ifNuidExists(String neuId) {
    boolean find = false;

    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery("FROM Students WHERE neuId = :studentNeuId");
      query.setParameter("studentNeuId", neuId);
      List list = query.list();
      if (!list.isEmpty()) {
        find = true;
      }
    } finally {
      session.close();
    }

    return find;
  }

  //==========================
  // EXTRACT SCRIPTS
  //==========================

  // THIS IS FOR MACHINE LEARNING AND PUBLIC SCRIPTS
  // How many students are at the {Seattle|Boston|Silicon Valley|Charlotte) campus?
  public int getTotalCurrentStudentsInACampus(Campus campus) {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "SELECT COUNT(*) FROM Students WHERE campus = :campus AND " +
                      "(enrollmentStatus = :enrollmentStatus1 OR enrollmentStatus = :enrollmentStatus2) ");
      query.setParameter("campus", campus);
      query.setParameter("enrollmentStatus1", EnrollmentStatus.FULL_TIME);
      query.setParameter("enrollmentStatus2", EnrollmentStatus.PART_TIME);
      return ((Long) query.list().get(0)).intValue();
    } finally {
      session.close();
    }
  }

  // THIS IS FOR MACHINE LEARNING SCRIPTS
  // How many students are in the ALIGN program?
  public int getTotalCurrentStudents() {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "SELECT COUNT(*) FROM Students WHERE " +
                      "enrollmentStatus = :enrollmentStatus1 OR enrollmentStatus = :enrollmentStatus2");
      query.setParameter("enrollmentStatus1", EnrollmentStatus.FULL_TIME);
      query.setParameter("enrollmentStatus2", EnrollmentStatus.PART_TIME);
      return ((Long) query.list().get(0)).intValue();
    } finally {
      session.close();
    }
  }

  // THIS IS FOR PUBLIC SCRIPTS
  public int getTotalStudents() {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "SELECT COUNT(*) FROM Students");
      return ((Long) query.list().get(0)).intValue();
    } finally {
      session.close();
    }
  }

  // THIS IS FOR MACHINE LEARNING SCRIPTS
  // HOW MANY STUDENTS GRADUATED FROM THE ALIGN PROGRAM?
  public int getTotalGraduatedStudents() {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "SELECT COUNT(*) FROM Students WHERE enrollmentStatus = :enrollmentStatus ");
      query.setParameter("enrollmentStatus", EnrollmentStatus.GRADUATED);
      return ((Long) query.list().get(0)).intValue();
    } finally {
      session.close();
    }
  }

  // THIS IS FOR MACHINE LEARNING SCRIPT
  // What is the drop out rate for Align?
  public int getTotalDropOutStudents() {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "SELECT COUNT(*) FROM Students WHERE enrollmentStatus = :enrollmentStatus");
      query.setParameter("enrollmentStatus", EnrollmentStatus.DROPPED_OUT);
      return ((Long) query.list().get(0)).intValue();
    } finally {
      session.close();
    }
  }

  // THIS IS FOR PUBLIC FACING SCRIPT
  // Total Full Time Students?
  public int getTotalFullTimeStudents() {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "SELECT COUNT(*) FROM Students WHERE enrollmentStatus = :enrollmentStatus");
      query.setParameter("enrollmentStatus", EnrollmentStatus.FULL_TIME);
      return ((Long) query.list().get(0)).intValue();
    } finally {
      session.close();
    }
  }

  // THIS IS FOR PUBLIC FACING SCRIPT
  // Total Part Time Students?
  public int getTotalPartTimeStudents() {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "SELECT COUNT(*) FROM Students WHERE enrollmentStatus = :enrollmentStatus");
      query.setParameter("enrollmentStatus", EnrollmentStatus.PART_TIME);
      return ((Long) query.list().get(0)).intValue();
    } finally {
      session.close();
    }
  }

  // THIS IS FOR PUBLIC FACING SCRIPT
  // Total Students With Scholarship?
  public int getTotalStudentsWithScholarship() {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "SELECT COUNT(*) FROM Students " +
                      "WHERE scholarship = true AND " +
                      "(enrollmentStatus = 'FULL_TIME' OR enrollmentStatus = 'PART_TIME') ");
      return ((Long) query.list().get(0)).intValue();
    } finally {
      session.close();
    }
  }

  // THIS IS FOR PUBLIC FACING SCRIPT
  // Race Breakdown?
  public List<MultipleValueAggregatedData> getRaceList() {
    String hql = "SELECT NEW model.alignpublic.MultipleValueAggregatedData ( " +
            "s.race, cast(Count(*) as integer) ) " +
            "FROM Students s " +
            "WHERE s.enrollmentStatus = 'FULL_TIME' OR s.enrollmentStatus = 'PART_TIME' " +
            "GROUP BY s.race " +
            "ORDER BY Count(*) DESC ";
    try {
      session = factory.openSession();
      TypedQuery<MultipleValueAggregatedData> query = session.createQuery(hql, MultipleValueAggregatedData.class);
      List<MultipleValueAggregatedData> list = query.getResultList();
      for (MultipleValueAggregatedData data : list) {
        data.setAnalyticTerm(MultipleValueAggregatedDataDao.LIST_OF_RACES);
      }
      return list;
    } finally {
      session.close();
    }
  }

  // THIS IS FOR PUBLIC FACING SCRIPT
  // State Breakdown?
  public List<MultipleValueAggregatedData> getStateList() {
    String hql = "SELECT NEW model.alignpublic.MultipleValueAggregatedData ( " +
            "s.state, cast(Count(*) as integer) ) " +
            "FROM Students s " +
            "WHERE s.enrollmentStatus = 'FULL_TIME' OR s.enrollmentStatus = 'PART_TIME' " +
            "GROUP BY s.state " +
            "ORDER BY Count(*) DESC ";
    try {
      session = factory.openSession();
      TypedQuery<MultipleValueAggregatedData> query = session.createQuery(hql, MultipleValueAggregatedData.class);
      List<MultipleValueAggregatedData> list = query.getResultList();
      for (MultipleValueAggregatedData data : list) {
        data.setAnalyticTerm(MultipleValueAggregatedDataDao.LIST_OF_STUDENTS_STATES);
      }
      return list;
    } finally {
      session.close();
    }
  }

  // THIS IS A SCRIPT FOR MACHINE LEARNING / PUBLIC
  // GET TOTAL CURRENT MALE STUDENTS

  /**
   * Get the total number of male students in database.
   *
   * @return the number of male students.
   */
  public int countMaleStudents() {
    session = factory.openSession();
    org.hibernate.query.Query query = session.createQuery("FROM Students WHERE gender = 'M' AND " +
            "(enrollmentStatus = 'FULL_TIME' OR enrollmentStatus = 'PART_TIME') ");
    List<Students> list = query.list();
    session.close();
    return list.size();
  }

  // THIS IS A SCRIPT FOR MACHINE LEARNING / PUBLIC
  // GET TOTAL CURRENT FEMALE STUDENTS

  /**
   * Get the total number of female students in database.
   *
   * @return the number of female students.
   */
  public int countFemaleStudents() {
    session = factory.openSession();
    org.hibernate.query.Query query = session.createQuery("FROM Students WHERE gender = 'F' AND " +
            "(enrollmentStatus = 'FULL_TIME' OR enrollmentStatus = 'PART_TIME') ");
    List<Students> list = query.list();
    session.close();
    return list.size();
  }
}
