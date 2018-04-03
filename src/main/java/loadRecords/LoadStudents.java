package loadRecords;

import com.csvreader.CsvReader;
import dao.alignprivate.PrivaciesDao;
import dao.alignprivate.StudentsDao;
import dao.alignpublic.StudentsPublicDao;
import enums.*;
import model.alignprivate.Privacies;
import model.alignprivate.Students;
import model.alignpublic.StudentsPublic;

import java.io.IOException;
import java.util.logging.Logger;

public class LoadStudents implements LoadFromCsv {
  private static Logger LOGGER = Logger.getLogger("InfoLogging");

  private CsvReader csvReader;
  private StudentsDao studentsDao;
  private StudentsPublicDao studentsPublicDao;
  private PrivaciesDao privaciesDao;

  public LoadStudents(boolean test) {
    if (test) {
      studentsDao = new StudentsDao(true);
      privaciesDao = new PrivaciesDao(true);
      studentsPublicDao = new StudentsPublicDao(true);
    } else {
      studentsDao = new StudentsDao(false);
      privaciesDao = new PrivaciesDao(false);
      studentsPublicDao = new StudentsPublicDao(false);
    }
  }

  @Override
  public void loadDatabase(String filePath) {
    try {
      csvReader = new CsvReader(filePath);
      csvReader.readHeaders();

      while (csvReader.readRecord()){
        String neuId = csvReader.get("NeuId").trim();
        String email = csvReader.get("Email").trim();
        String firstName = csvReader.get("FirstName").trim();
        String middleName = csvReader.get("MiddleName").trim();
        String lastName = csvReader.get("LastName").trim();
        Gender gender = Gender.valueOf(csvReader.get("Gender").trim());
        String visa = csvReader.get("VisaType").trim();
        boolean scholarship = csvReader.get("Scholarship").trim().equals("Yes") ? true : false;
        String phone = csvReader.get("LocalPhone").trim();
        String address = csvReader.get("LocalAddress").trim();
        String state = csvReader.get("State").trim();
        String city = csvReader.get("City").trim();
        String zip = csvReader.get("Zip").trim();
        EnrollmentStatus status = EnrollmentStatus.valueOf(csvReader.get("EnrollmentStatus").trim());
        Campus campus = Campus.valueOf(csvReader.get("Campus").trim());
        Term entryTerm = Term.valueOf(csvReader.get("EntryTerm").trim());
        int entryYear = Integer.parseInt(csvReader.get("EntryYear").trim());
        Term graduateTerm = Term.valueOf(csvReader.get("ExpectedGraduateTerm").trim());
        int graduateYear = Integer.parseInt(csvReader.get("ExpectedGraduateYear").trim());
        DegreeCandidacy degree = DegreeCandidacy.valueOf(csvReader.get("Degree").trim());
        String race = csvReader.get("Race").trim();

        Students student = new Students(neuId, email, firstName, middleName, lastName, gender, race,
                visa, phone, address, state, city, zip, entryTerm, entryYear, graduateTerm, graduateYear,
                status, campus, degree, null, false);

        if (studentsDao.ifNuidExists(neuId)) {
          int publicId = studentsDao.getStudentPublicId(neuId);
          student.setPublicId(publicId);
          studentsDao.updateStudentRecord(student);
          LOGGER.info("Update student " + student);
        } else {
          studentsDao.addStudent(student);
          int publicId = studentsDao.getStudentPublicId(neuId);

          StudentsPublic studentsPublic = new StudentsPublic(publicId, graduateYear, true);
          studentsPublicDao.createStudent(studentsPublic);

          Privacies privacy = new Privacies();
          privacy.setNeuId(neuId);
          privacy.setPublicId(publicId);
          privacy.setVisibleToPublic(true);
          privaciesDao.createPrivacy(privacy);

          LOGGER.info("Add student " + student + " and privacy " + privacy + " and public student" + publicId);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      csvReader.close();
//      studentsDao.getFactory().close();
//      privaciesDao.getFactory().close();
//      studentsPublicDao.getFactory().close();
    }
  }
}
