package loadRecords.LoadPrivate;

import com.csvreader.CsvReader;
import dao.alignprivate.PriorEducationsDao;
import dao.alignprivate.StudentsDao;
import enums.DegreeCandidacy;
import loadRecords.LoadFromCsv;
import model.alignprivate.PriorEducations;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class LoadPriorEducations implements LoadFromCsv {
  private static Logger LOGGER = Logger.getLogger("InfoLogging");
  private CsvReader csvReader;
  private PriorEducationsDao priorEducationsDao;
  private StudentsDao studentsDao;

  public LoadPriorEducations() {
    priorEducationsDao = new PriorEducationsDao();
    studentsDao = new StudentsDao();
  }

  @Override
  public void loadDatabase(String filePath) {
    try {
      csvReader = new CsvReader(filePath);
      csvReader.readHeaders();
      SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

      while (csvReader.readRecord()){
        String neuId = csvReader.get("NeuId").trim();
        String institutionName = csvReader.get("Institution_Name").trim();
        String major = csvReader.get("Major").trim();
        Date graduation = dateFormat.parse(csvReader.get("Graduation_Date").trim());
        float gpa = Float.valueOf(csvReader.get("GPA").trim());
        DegreeCandidacy degree = DegreeCandidacy.valueOf(csvReader.get("Degree").trim());

        PriorEducations priorEducation = new PriorEducations(neuId, institutionName, major, graduation, gpa, degree);
        PriorEducations existEducation = priorEducationsDao.getPriorEducation(neuId, institutionName, major, degree);

        if (!studentsDao.ifNuidExists(neuId)) {
          LOGGER.info("No student exists for " + neuId);
        } else if (existEducation != null) {
          priorEducation.setPriorEducationId(existEducation.getPriorEducationId());
          priorEducationsDao.updatePriorEducation(priorEducation);
          LOGGER.info("Update prior education " + priorEducation);
        } else {
          priorEducationsDao.createPriorEducation(priorEducation);
          LOGGER.info("Add prior education " + priorEducation);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } finally {
      csvReader.close();
      priorEducationsDao.getFactory().close();
      studentsDao.getFactory().close();
    }
  }
}
