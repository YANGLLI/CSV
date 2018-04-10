package extractToPublic;

import dao.alignprivate.PriorEducationsDao;
import dao.alignprivate.StudentsDao;
import dao.alignprivate.WorkExperiencesDao;
import dao.alignpublic.MultipleValueAggregatedDataDao;
import dao.alignpublic.SingleValueAggregatedDataDao;
import enums.Campus;
import model.alignpublic.MultipleValueAggregatedData;
import model.alignpublic.SingleValueAggregatedData;

import java.util.List;

public class AggregatedDataLoader {
  StudentsDao studentsDao;
  WorkExperiencesDao workExperiencesDao;
  PriorEducationsDao priorEducationsDao;
  SingleValueAggregatedDataDao singleValueAggregatedDataDao;
  MultipleValueAggregatedDataDao multipleValueAggregatedDataDao;

  public AggregatedDataLoader(boolean test) {
    if (test) {
      studentsDao = new StudentsDao(true);
      workExperiencesDao = new WorkExperiencesDao(true);
      priorEducationsDao = new PriorEducationsDao(true);
      singleValueAggregatedDataDao = new SingleValueAggregatedDataDao(true);
      multipleValueAggregatedDataDao = new MultipleValueAggregatedDataDao(true);
    } else {
      studentsDao = new StudentsDao(false);
      workExperiencesDao = new WorkExperiencesDao(false);
      priorEducationsDao = new PriorEducationsDao(false);
      singleValueAggregatedDataDao = new SingleValueAggregatedDataDao();
      multipleValueAggregatedDataDao = new MultipleValueAggregatedDataDao();
    }
  }

  public void extractAndLoad() {
    extractAndLoadTotalStudents();
    extractAndLoadTotalCurrentStudents();
    extractAndLoadTotalStudentsWithScholarship();
    extractAndLoadTotalMaleStudents();
    extractAndLoadTotalFemaleStudents();
    extractAndLoadTotalFullTimeStudents();
    extractAndLoadTotalPartTimeStudents();
    extractAndLoadTotalGraduatedStudents();
    extractAndLoadTotalDroppedOutStudents();
    extractAndLoadTotalStudentsGotJob();
    extractAndLoadTotalStudentsInBoston();
    extractAndLoadTotalStudentsInSeattle();
    extractAndLoadTotalStudentsInCharlotte();
    extractAndLoadTotalStudentsInSiliconValley();
    extractAndLoadListOfEmployers();
    extractAndLoadListOfBachelorMajors();
    extractAndLoadListOfDegrees();
//    extractAndLoadListOfRaces();
    extractAndLoadListOfStates();
  }

  private void extractAndLoadTotalStudents() {
    SingleValueAggregatedData totalStudents = new SingleValueAggregatedData();
    totalStudents.setAnalyticKey(SingleValueAggregatedDataDao.TOTAL_STUDENTS);
    totalStudents.setAnalyticValue(studentsDao.getTotalStudents());
    singleValueAggregatedDataDao.saveOrUpdateData(totalStudents);
  }

  private void extractAndLoadTotalCurrentStudents() {
    SingleValueAggregatedData totalCurrentStudents = new SingleValueAggregatedData();
    totalCurrentStudents.setAnalyticKey(SingleValueAggregatedDataDao.TOTAL_CURRENT_STUDENTS);
    totalCurrentStudents.setAnalyticValue(studentsDao.getTotalCurrentStudents());
    singleValueAggregatedDataDao.saveOrUpdateData(totalCurrentStudents);
  }

  private void extractAndLoadTotalStudentsWithScholarship() {
    SingleValueAggregatedData totalStudentsWithScholarship = new SingleValueAggregatedData();
    totalStudentsWithScholarship.setAnalyticKey(SingleValueAggregatedDataDao.TOTAL_STUDENTS_WITH_SCHOLARSHIP);
    totalStudentsWithScholarship.setAnalyticValue(studentsDao.getTotalStudentsWithScholarship());
    System.out.println(studentsDao.getTotalStudentsWithScholarship());
    singleValueAggregatedDataDao.saveOrUpdateData(totalStudentsWithScholarship);
  }

  private void extractAndLoadTotalMaleStudents() {
    SingleValueAggregatedData totalMaleStudents = new SingleValueAggregatedData();
    totalMaleStudents.setAnalyticKey(SingleValueAggregatedDataDao.TOTAL_MALE_STUDENTS);
    totalMaleStudents.setAnalyticValue(studentsDao.countMaleStudents());
    singleValueAggregatedDataDao.saveOrUpdateData(totalMaleStudents);
  }

  private void extractAndLoadTotalFemaleStudents() {
    SingleValueAggregatedData totalFemaleStudents = new SingleValueAggregatedData();
    totalFemaleStudents.setAnalyticKey(SingleValueAggregatedDataDao.TOTAL_FEMALE_STUDENTS);
    totalFemaleStudents.setAnalyticValue(studentsDao.countFemaleStudents());
    singleValueAggregatedDataDao.saveOrUpdateData(totalFemaleStudents);
  }

  private void extractAndLoadTotalFullTimeStudents() {
    SingleValueAggregatedData totalFullTimeStudents = new SingleValueAggregatedData();
    totalFullTimeStudents.setAnalyticKey(SingleValueAggregatedDataDao.TOTAL_FULL_TIME_STUDENTS);
    totalFullTimeStudents.setAnalyticValue(studentsDao.getTotalFullTimeStudents());
    singleValueAggregatedDataDao.saveOrUpdateData(totalFullTimeStudents);
  }

  private void extractAndLoadTotalPartTimeStudents() {
    SingleValueAggregatedData totalPartTimeStudents = new SingleValueAggregatedData();
    totalPartTimeStudents.setAnalyticKey(SingleValueAggregatedDataDao.TOTAL_PART_TIME_STUDENTS);
    totalPartTimeStudents.setAnalyticValue(studentsDao.getTotalPartTimeStudents());
    singleValueAggregatedDataDao.saveOrUpdateData(totalPartTimeStudents);
  }

  private void extractAndLoadTotalGraduatedStudents() {
    SingleValueAggregatedData totalGraduatedStudents = new SingleValueAggregatedData();
    totalGraduatedStudents.setAnalyticKey(SingleValueAggregatedDataDao.TOTAL_GRADUATED_STUDENTS);
    totalGraduatedStudents.setAnalyticValue(studentsDao.getTotalGraduatedStudents());
    singleValueAggregatedDataDao.saveOrUpdateData(totalGraduatedStudents);
  }

  private void extractAndLoadTotalDroppedOutStudents() {
    SingleValueAggregatedData totalDroppedOutStudents = new SingleValueAggregatedData();
    totalDroppedOutStudents.setAnalyticKey(SingleValueAggregatedDataDao.TOTAL_STUDENTS_DROPPED_OUT);
    totalDroppedOutStudents.setAnalyticValue(studentsDao.getTotalDropOutStudents());
    singleValueAggregatedDataDao.saveOrUpdateData(totalDroppedOutStudents);
  }

  private void extractAndLoadTotalStudentsGotJob() {
    SingleValueAggregatedData totalStudentsGotJob = new SingleValueAggregatedData();
    totalStudentsGotJob.setAnalyticKey(SingleValueAggregatedDataDao.TOTAL_STUDENTS_GOT_JOB);
    totalStudentsGotJob.setAnalyticValue(workExperiencesDao.getTotalStudentsGotJob());
    singleValueAggregatedDataDao.saveOrUpdateData(totalStudentsGotJob);
  }

  private void extractAndLoadTotalStudentsInBoston() {
    SingleValueAggregatedData totalStudents = new SingleValueAggregatedData();
    totalStudents.setAnalyticKey(SingleValueAggregatedDataDao.TOTAL_STUDENTS_IN_BOSTON);
    totalStudents.setAnalyticValue(studentsDao.getTotalCurrentStudentsInACampus(Campus.BOSTON));
    singleValueAggregatedDataDao.saveOrUpdateData(totalStudents);
  }

  private void extractAndLoadTotalStudentsInSeattle() {
    SingleValueAggregatedData totalStudents = new SingleValueAggregatedData();
    totalStudents.setAnalyticKey(SingleValueAggregatedDataDao.TOTAL_STUDENTS_IN_SEATTLE);
    totalStudents.setAnalyticValue(studentsDao.getTotalCurrentStudentsInACampus(Campus.SEATTLE));
    singleValueAggregatedDataDao.saveOrUpdateData(totalStudents);
  }

  private void extractAndLoadTotalStudentsInCharlotte() {
    SingleValueAggregatedData totalStudents = new SingleValueAggregatedData();
    totalStudents.setAnalyticKey(SingleValueAggregatedDataDao.TOTAL_STUDENTS_IN_CHARLOTTE);
    totalStudents.setAnalyticValue(studentsDao.getTotalCurrentStudentsInACampus(Campus.CHARLOTTE));
    singleValueAggregatedDataDao.saveOrUpdateData(totalStudents);
  }

  private void extractAndLoadTotalStudentsInSiliconValley() {
    SingleValueAggregatedData totalStudents = new SingleValueAggregatedData();
    totalStudents.setAnalyticKey(SingleValueAggregatedDataDao.TOTAL_STUDENTS_IN_SILICON_VALLEY);
    totalStudents.setAnalyticValue(studentsDao.getTotalCurrentStudentsInACampus(Campus.SILICON_VALLEY));
    singleValueAggregatedDataDao.saveOrUpdateData(totalStudents);
  }

  private void extractAndLoadListOfEmployers() {
    List<MultipleValueAggregatedData> listOfEmployers = workExperiencesDao.getStudentEmployers();
    multipleValueAggregatedDataDao.saveOrUpdateList(listOfEmployers);
  }

  private void extractAndLoadListOfBachelorMajors() {
    List<MultipleValueAggregatedData> listOfMajors = priorEducationsDao.getStudentBachelorMajors();
    multipleValueAggregatedDataDao.saveOrUpdateList(listOfMajors);
  }

  private void extractAndLoadListOfDegrees() {
    List<MultipleValueAggregatedData> listOfDegrees = priorEducationsDao.getDegreeList();
    multipleValueAggregatedDataDao.saveOrUpdateList(listOfDegrees);
  }

//  private void extractAndLoadListOfRaces() {
//    List<MultipleValueAggregatedData> listOfRaces = studentsDao.getRaceList();
//    multipleValueAggregatedDataDao.saveOrUpdateList(listOfRaces);
//  }

  private void extractAndLoadListOfStates() {
    List<MultipleValueAggregatedData> listOfStates = studentsDao.getStateList();
    multipleValueAggregatedDataDao.saveOrUpdateList(listOfStates);
  }
}
