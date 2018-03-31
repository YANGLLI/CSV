package loadRecords.LoadPrivate;

import com.csvreader.CsvReader;
import dao.alignprivate.PrivaciesDao;
import dao.alignprivate.StudentsDao;
import enums.*;
import loadRecords.LoadFromCsv;
import model.alignprivate.Privacies;
import model.alignprivate.Students;

import java.io.IOException;
import java.util.logging.Logger;

public class LoadStudents implements LoadFromCsv {
  private static Logger LOGGER = Logger.getLogger("InfoLogging");

  private CsvReader csvReader;
  private StudentsDao studentsDao;
  private PrivaciesDao privaciesDao;

  public LoadStudents() {
    studentsDao = new StudentsDao();
    privaciesDao = new PrivaciesDao();
  }

  @Override
  public void loadDatabase(String filePath) {
    try {
      csvReader = new CsvReader(filePath);
      csvReader.readHeaders();

      while (csvReader.readRecord()){
        String neuId = csvReader.get("NeuId").trim();
        String email = csvReader.get("Email").trim();
        String firstName = csvReader.get("First_Name").trim();
        String middleName = csvReader.get("Middle_Name").trim();
        String lastName = csvReader.get("Last_Name").trim();
        Gender gender = Gender.valueOf(csvReader.get("Gender").trim());
        String visa = csvReader.get("Visa_Type").trim();
        boolean scholarship = csvReader.get("Scholarship").trim().equals("Yes")?true:false;
        String phone = csvReader.get("Local_Phone").trim();
        String address = csvReader.get("Local_Address").trim();
        String state = csvReader.get("State").trim();
        String city = csvReader.get("City").trim();
        String zip = csvReader.get("Zip").trim();
        EnrollmentStatus status = EnrollmentStatus.valueOf(csvReader.get("Enrollment_Status").trim());
        Campus campus = Campus.valueOf(csvReader.get("Campus").trim());
        Term entryTerm = Term.valueOf(csvReader.get("Entry_Term").trim());
        int entryYear = Integer.parseInt(csvReader.get("Entry_Year").trim());
        Term graduateTerm = Term.valueOf(csvReader.get("Expected_Graduate_Term").trim());
        int graduateYear = Integer.parseInt(csvReader.get("Expected_Graduate_Year").trim());
        DegreeCandidacy degree = DegreeCandidacy.valueOf(csvReader.get("Degree").trim());
        String race = csvReader.get("Race").trim();

        Students student = new Students(neuId, email, firstName, middleName, lastName, gender, race,
                visa, phone, address, state, city, zip, entryTerm, entryYear, graduateTerm, graduateYear,
                status, campus, degree, null, false);

        if (studentsDao.ifNuidExists(neuId)) {
          studentsDao.updateStudentRecord(student);
          LOGGER.info("Update student " + student);
        } else {
          studentsDao.addStudent(student);
          int publicId = studentsDao.getStudentPublicId(neuId);

          Privacies privacy = new Privacies();
          privacy.setNeuId(neuId);
          privacy.setPublicId(publicId);
          privacy.setVisibleToPublic(true);
          privaciesDao.createPrivacy(privacy);
          LOGGER.info("Add student " + student + " and privacy " + privacy);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      csvReader.close();
      studentsDao.getFactory().close();
      privaciesDao.getFactory().close();
    }
  }
}
