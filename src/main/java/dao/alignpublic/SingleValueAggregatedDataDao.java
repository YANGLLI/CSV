package dao.alignpublic;

import model.alignpublic.SingleValueAggregatedData;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SingleValueAggregatedDataDao {
  public static final String TOTAL_GRADUATED_STUDENTS = "TotalGraduatedStudents";
  public static final String TOTAL_CURRENT_STUDENTS = "TotalCurrentStudents";
  public static final String TOTAL_STUDENTS = "TotalStudents";
  public static final String TOTAL_STUDENTS_DROPPED_OUT = "TotalStudentsDroppedOut";
  public static final String TOTAL_STUDENTS_GOT_JOB = "TotalStudentsGotJob";
  public static final String TOTAL_STUDENTS_IN_BOSTON = "TotalStudentsInBoston";
  public static final String TOTAL_STUDENTS_IN_SEATTLE = "TotalStudentsInSeattle";
  public static final String TOTAL_STUDENTS_IN_SILICON_VALLEY = "TotalStudentsInSiliconValley";
  public static final String TOTAL_STUDENTS_IN_CHARLOTTE = "TotalStudentsInCharlotte";
  public static final String TOTAL_MALE_STUDENTS = "TotalMaleStudents";
  public static final String TOTAL_FEMALE_STUDENTS = "TotalFemaleStudents";
  public static final String TOTAL_FULL_TIME_STUDENTS = "TotalFullTimeStudents";
  public static final String TOTAL_PART_TIME_STUDENTS = "TotalPartTimeStudents";
  public static final String TOTAL_STUDENTS_WITH_SCHOLARSHIP = "TotalStudentsWithScholarship";

  private SessionFactory factory;
  private Session session;

  public SingleValueAggregatedDataDao() {
    this.factory = PublicSessionFactory.getFactory();
  }

  public SingleValueAggregatedDataDao(boolean test) {
    if (test) {
      this.factory = PublicTestSessionFactory.getFactory();
    }
  }

//  public int getTotalGraduatedStudents() {
//    return findValueByKey(TOTAL_GRADUATED_STUDENTS);
//  }
//
//  public int getTotalStudents() {
//    return findValueByKey(TOTAL_STUDENTS);
//  }
//
//  public int getTotalCurrentStudents() {
//    return findValueByKey(TOTAL_CURRENT_STUDENTS);
//  }
//
//  public int getTotalStudentsWithScholarship() {
//    return findValueByKey(TOTAL_STUDENTS_WITH_SCHOLARSHIP);
//  }
//
//  public int getTotalMaleStudents() {
//    return findValueByKey(TOTAL_MALE_STUDENTS);
//  }
//
//  public int getTotalFemaleStudents() {
//    return findValueByKey(TOTAL_FEMALE_STUDENTS);
//  }
//
//  public int getTotalFullTimeStudents() {
//    return findValueByKey(TOTAL_FULL_TIME_STUDENTS);
//  }
//
//  public int getTotalPartTimeStudents() {
//    return findValueByKey(TOTAL_PART_TIME_STUDENTS);
//  }
//
//  public int getTotalDroppedOutStudents() {
//    return findValueByKey(TOTAL_STUDENTS_DROPPED_OUT);
//  }
//
//  public int getTotalStudentsGotJob() {
//    return findValueByKey(TOTAL_STUDENTS_GOT_JOB);
//  }
//
//  public int getTotalStudentsInBoston() {
//    return findValueByKey(TOTAL_STUDENTS_IN_BOSTON);
//  }
//
//  public int getTotalStudentsInSeattle() {
//    return findValueByKey(TOTAL_STUDENTS_IN_SEATTLE);
//  }
//
//  public int getTotalStudentsInCharlotte() {
//    return findValueByKey(TOTAL_STUDENTS_IN_CHARLOTTE);
//  }
//
//  public int getTotalStudentsInSiliconValley() {
//    return findValueByKey(TOTAL_STUDENTS_IN_SILICON_VALLEY);
//  }

//  private int findValueByKey(String analyticKey) {
//    try {
//      session = factory.openSession();
//      org.hibernate.query.Query query = session.createQuery(
//              "SELECT analyticValue FROM SingleValueAggregatedData WHERE analyticKey = :analyticKey ");
//      query.setParameter("analyticKey", analyticKey);
//      return (int) query.list().get(0);
//    } finally {
//      session.close();
//    }
//  }

  public SingleValueAggregatedData findTotalGraduatedStudentsData() {
    return findDataByKey(TOTAL_GRADUATED_STUDENTS);
  }

  public SingleValueAggregatedData findTotalStudentsData() {
    return findDataByKey(TOTAL_STUDENTS);
  }

  public SingleValueAggregatedData findTotalCurrentStudentsData() {
    return findDataByKey(TOTAL_CURRENT_STUDENTS);
  }

  public SingleValueAggregatedData findTotalStudentsWithScholarshipData() {
    return findDataByKey(TOTAL_STUDENTS_WITH_SCHOLARSHIP);
  }

  public SingleValueAggregatedData findTotalMaleStudentsData() {
    return findDataByKey(TOTAL_MALE_STUDENTS);
  }

  public SingleValueAggregatedData findTotalFemaleStudentsData() {
    return findDataByKey(TOTAL_FEMALE_STUDENTS);
  }

  public SingleValueAggregatedData findTotalFullTimeStudentsData() {
    return findDataByKey(TOTAL_FULL_TIME_STUDENTS);
  }

  public SingleValueAggregatedData findTotalPartTimeStudentsData() {
    return findDataByKey(TOTAL_PART_TIME_STUDENTS);
  }

  public SingleValueAggregatedData findTotalDroppedOutStudentsData() {
    return findDataByKey(TOTAL_STUDENTS_DROPPED_OUT);
  }

  public SingleValueAggregatedData findTotalStudentsGotJobData() {
    return findDataByKey(TOTAL_STUDENTS_GOT_JOB);
  }

  public SingleValueAggregatedData findTotalStudentsInBostonData() {
    return findDataByKey(TOTAL_STUDENTS_IN_BOSTON);
  }

  public SingleValueAggregatedData findTotalStudentsInSeattleData() {
    return findDataByKey(TOTAL_STUDENTS_IN_SEATTLE);
  }

  public SingleValueAggregatedData findTotalStudentsInCharlotteData() {
    return findDataByKey(TOTAL_STUDENTS_IN_CHARLOTTE);
  }

  public SingleValueAggregatedData findTotalStudentsInSiliconValleyData() {
    return findDataByKey(TOTAL_STUDENTS_IN_SILICON_VALLEY);
  }

  private SingleValueAggregatedData findDataByKey(String analyticKey) {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "FROM SingleValueAggregatedData WHERE analyticKey = :analyticKey ");
      query.setParameter("analyticKey", analyticKey);
      List<SingleValueAggregatedData> list = query.list();
      if (list.isEmpty()) {
        return null;
      }
      return list.get(0);
    } finally {
      session.close();
    }
  }

  public boolean saveOrUpdateData(SingleValueAggregatedData updatedData) {
    Transaction tx = null;
    SingleValueAggregatedData data = findDataByKey(updatedData.getAnalyticKey());
    if (data == null) {
      throw new HibernateException("Data cannot be found.");
    }
    try {
      session = factory.openSession();
      tx = session.beginTransaction();
      session.saveOrUpdate(updatedData);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      throw new HibernateException(e);
    } finally {
      session.close();
    }

    return true;
  }
}
