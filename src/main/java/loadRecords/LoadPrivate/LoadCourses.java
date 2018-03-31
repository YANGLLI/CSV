package loadRecords.LoadPrivate;

import com.csvreader.CsvReader;
import dao.alignprivate.CoursesDao;
import loadRecords.LoadFromCsv;
import model.alignprivate.Courses;

import java.io.IOException;
import java.util.logging.Logger;

public class LoadCourses implements LoadFromCsv {
  private static Logger LOGGER = Logger.getLogger("InfoLogging");

  private CsvReader csvReader;
  private CoursesDao coursesDao;

  public LoadCourses() {
    coursesDao = new CoursesDao();
  }

  @Override
  public void loadDatabase(String filePath) {
    try {
      csvReader = new CsvReader(filePath);
      csvReader.readHeaders();

      while (csvReader.readRecord()){
        String courseId = csvReader.get("CourseId").trim();
        String courseName = csvReader.get("Course_Name").trim();
        String description = csvReader.get("Description").trim();

        Courses course = new Courses(courseId,courseName,description);

        if (coursesDao.ifCourseidExists(courseId)) {
          coursesDao.updateCourse(course);
          LOGGER.info("Update course " + course);
        } else {
          coursesDao.createCourse(course);
          LOGGER.info("Add course " + course);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      csvReader.close();
      coursesDao.getFactory().close();
    }
  }
}
