package loadRecords;

import com.csvreader.CsvReader;
import dao.alignadmin.ElectivesAdminDao;
import dao.alignprivate.CoursesDao;
import dao.alignprivate.ElectivesDao;
import dao.alignprivate.StudentsDao;
import enums.Term;
import loadRecords.LoadFromCsv;
import model.alignadmin.ElectivesAdmin;
import model.alignprivate.Electives;

import java.io.IOException;
import java.util.logging.Logger;

public class LoadElectives implements LoadFromCsv {
  private static Logger LOGGER = Logger.getLogger("InfoLogging");

  private CsvReader csvReader;
  private ElectivesDao electivesDao;
  private StudentsDao studentsDao;
  private CoursesDao coursesDao;
  private ElectivesAdminDao electivesAdminDao;

  public LoadElectives(boolean test) {
    if (test) {
      electivesDao = new ElectivesDao(true);
      coursesDao = new CoursesDao(true);
      studentsDao = new StudentsDao(true);
      electivesAdminDao = new ElectivesAdminDao(true);
    } else {
      electivesDao = new ElectivesDao(false);
      studentsDao = new StudentsDao(false);
      coursesDao = new CoursesDao(false);
      electivesAdminDao = new ElectivesAdminDao(false);
    }
  }

  /**
   * Load Electives table.
   * If elective is already exists, it will perform a update.
   * If elective is new, it will insert a new record.
   *
   * @param filePath
   */
  @Override
  public void loadDatabase(String filePath) {
    try {
      csvReader = new CsvReader(filePath);
      csvReader.readHeaders();

      while (csvReader.readRecord()){
        String neuId = csvReader.get("NeuId").trim();
        String courseId = csvReader.get("CourseId").trim();
        String courseName = csvReader.get("CourseName").trim();
        Term term = Term.valueOf(csvReader.get("Term").trim());
        int year = Integer.valueOf(csvReader.get("Year").trim());
        String gpa = csvReader.get("GPA").trim();
        boolean retake = csvReader.get("Retake").trim().equals("Yes")? true:false;
        boolean plagiarism = csvReader.get("Plagiarism").trim().equals("Yes")? true:false;

        Electives elective = new Electives(neuId, courseId, courseName, term, year);
        Electives existElective = electivesDao.getElective(neuId, courseId, term, year);

        if (!studentsDao.ifNuidExists(neuId)) {
          LOGGER.info("No student exists for " + neuId);
        } else if (!coursesDao.ifCourseidExists(courseId)) {
          LOGGER.info("No course exists for " + courseId + courseName);
        } else if (existElective != null) {
          elective.setElectiveId(existElective.getElectiveId());
          electivesDao.updateElectives(elective);
          LOGGER.info("Update elective " + elective);
        } else {
          electivesDao.addElective(elective);
          ElectivesAdmin electivesAdmin = new ElectivesAdmin(neuId, courseId, courseName, term, year, retake, gpa, plagiarism);
          electivesAdminDao.addElective(electivesAdmin);
          LOGGER.info("Add elective " + elective);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      csvReader.close();
//      electivesDao.getFactory().close();
//      studentsDao.getFactory().close();
//      coursesDao.getFactory().close();
    }
  }
}
