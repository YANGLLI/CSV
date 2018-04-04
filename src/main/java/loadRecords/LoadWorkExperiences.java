package loadRecords;

import com.csvreader.CsvReader;
import dao.alignprivate.StudentsDao;
import dao.alignprivate.WorkExperiencesDao;
import dao.alignpublic.WorkExperiencesPublicDao;
import loadRecords.LoadFromCsv;
import model.alignprivate.WorkExperiences;
import model.alignpublic.WorkExperiencesPublic;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class LoadWorkExperiences implements LoadFromCsv {
  private static Logger LOGGER = Logger.getLogger("InfoLogging");

  private CsvReader csvReader;
  private WorkExperiencesDao workExperiencesDao;
  private WorkExperiencesPublicDao workExperiencesPublicDao;
  private StudentsDao studentsDao;

  public LoadWorkExperiences(boolean test) {
    if (test) {
      workExperiencesDao = new WorkExperiencesDao(true);
      studentsDao = new StudentsDao(true);
      workExperiencesPublicDao = new WorkExperiencesPublicDao(true);
    } else {
      workExperiencesDao = new WorkExperiencesDao(false);
      studentsDao = new StudentsDao(false);
      workExperiencesPublicDao = new WorkExperiencesPublicDao(false);
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
        String companyName = csvReader.get("CompanyName").trim();
        Date startDate = dateFormat.parse(csvReader.get("StartDate").trim());
        String start_Date = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
        Date endDate = dateFormat.parse(csvReader.get("EndDate").trim());
        String title = csvReader.get("Title").trim();
        String description = csvReader.get("Description").trim();
        boolean currentJob = csvReader.get("CurrentJob").trim().equals("Yes");
        boolean coop = csvReader.get("Coop").trim().equals("Yes");

        WorkExperiences experience = new WorkExperiences(neuId, companyName, startDate, endDate, currentJob, coop, title, description);
        WorkExperiences existExperience = workExperiencesDao.getWorkExperience(neuId, companyName, startDate);

        if(!studentsDao.ifNuidExists(neuId)) {
          LOGGER.info("No student exists for " + neuId);
        } else if (existExperience!= null) {
          experience.setWorkExperienceId(existExperience.getWorkExperienceId());
          workExperiencesDao.updateWorkExperience(experience);
          LOGGER.info("Update work experience " + experience);
        } else {
          workExperiencesDao.createWorkExperience(experience);
          if (coop) {
            int publicId = studentsDao.getStudentPublicId(neuId);
            WorkExperiencesPublic workExperiencesPublic = new WorkExperiencesPublic();
            workExperiencesPublic.setPublicId(publicId);
            workExperiencesPublic.setCoop(companyName);
            workExperiencesPublicDao.createWorkExperience(workExperiencesPublic);
            LOGGER.info("Add public work experience ");
          }
          LOGGER.info("Add work experience " + experience);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } finally {
      csvReader.close();
//      workExperiencesDao.getFactory().close();
//      workExperiencesPublicDao.getFactory().close();
//      studentsDao.getFactory().close();
    }
  }
}
