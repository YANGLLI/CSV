package loadRecords;

import com.csvreader.CsvReader;
import dao.alignprivate.PriorEducationsDao;
import dao.alignprivate.StudentsDao;
import dao.alignpublic.UndergraduatesPublicDao;
import enums.DegreeCandidacy;
import loadRecords.LoadFromCsv;
import model.alignprivate.PriorEducations;
import model.alignpublic.UndergraduatesPublic;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class LoadPriorEducations implements LoadFromCsv {
  private static Logger LOGGER = Logger.getLogger("InfoLogging");
  private CsvReader csvReader;
  private PriorEducationsDao priorEducationsDao;
  private UndergraduatesPublicDao undergraduatesPublicDao;
  private StudentsDao studentsDao;

  public LoadPriorEducations(boolean test) {
    if (test) {
      priorEducationsDao = new PriorEducationsDao(true);
      studentsDao = new StudentsDao(true);
      undergraduatesPublicDao = new UndergraduatesPublicDao(true);
    } else {
      priorEducationsDao = new PriorEducationsDao(false);
      studentsDao = new StudentsDao(false);
      undergraduatesPublicDao = new UndergraduatesPublicDao(false);
    }
  }

  @Override
  public void loadDatabase(String filePath) {
    try {
      csvReader = new CsvReader(filePath);
      csvReader.readHeaders();
      SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

      while (csvReader.readRecord()){
        String neuId = csvReader.get("NeuId").trim();
        String institutionName = csvReader.get("InstitutionName").trim();
        String major = csvReader.get("Major").trim();
        Date graduation = dateFormat.parse(csvReader.get("GraduationDate").trim());
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
          if (degree.equals(DegreeCandidacy.BACHELORS)) {
            int publicId = studentsDao.getStudentPublicId(neuId);
            UndergraduatesPublic undergraduatesPublic = new UndergraduatesPublic();
            undergraduatesPublic.setPublicId(publicId);
            undergraduatesPublic.setUndergradDegree(degree.toString());
            undergraduatesPublic.setUndergradSchool(institutionName);

            undergraduatesPublicDao.createUndergraduate(undergraduatesPublic);
          }
          LOGGER.info("Add prior education " + priorEducation);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } finally {
      csvReader.close();
//      priorEducationsDao.getFactory().close();
//      undergraduatesPublicDao.getFactory().close();
//      studentsDao.getFactory().close();
    }
  }
}
