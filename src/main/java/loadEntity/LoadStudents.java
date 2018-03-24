package loadEntity;

import com.csvreader.CsvReader;
import dao.StudentsDao;
import enums.*;
import model.Students;

import java.io.IOException;

public class LoadStudents implements LoadFromCsv {
  private CsvReader csvReader;
  private StudentsDao studentsDao;

  @Override
  public void loadDatabase(String filePath) {
    studentsDao = new StudentsDao();

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
        } else {
          studentsDao.addStudent(student);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      csvReader.close();
      studentsDao.getFactory().close();
    }
  }
}
