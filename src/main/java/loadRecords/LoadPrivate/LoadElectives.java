package loadRecords.LoadPrivate;

import com.csvreader.CsvReader;
import dao.alignprivate.CoursesDao;
import dao.alignprivate.ElectivesDao;
import dao.alignprivate.StudentsDao;
import enums.Term;
import loadRecords.LoadFromCsv;
import model.alignprivate.Electives;

import java.io.IOException;
import java.util.logging.Logger;

public class LoadElectives implements LoadFromCsv {
  private static Logger LOGGER = Logger.getLogger("InfoLogging");

  private CsvReader csvReader;
  private ElectivesDao electivesDao;
  private StudentsDao studentsDao;
  private CoursesDao coursesDao;

  public LoadElectives() {
    electivesDao = new ElectivesDao();
    studentsDao = new StudentsDao();
    coursesDao = new CoursesDao();
  }

  @Override
  public void loadDatabase(String filePath) {
    try {
      csvReader = new CsvReader(filePath);
      csvReader.readHeaders();

      while (csvReader.readRecord()){
        String neuId = csvReader.get("NeuId").trim();
        String courseId = csvReader.get("CourseId").trim();
        String courseName = csvReader.get("Course_Name").trim();
        Term term = Term.valueOf(csvReader.get("Term").trim());
        int year = Integer.valueOf(csvReader.get("Year").trim());

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
          LOGGER.info("Add elective " + elective);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      csvReader.close();
      electivesDao.getFactory().close();
      studentsDao.getFactory().close();
      coursesDao.getFactory().close();
    }
  }
}
