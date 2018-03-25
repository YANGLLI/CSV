package loadRecords;

import com.csvreader.CsvReader;
import dao.StudentsDao;
import dao.WorkExperiencesDao;
import model.WorkExperiences;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class LoadWorkExperiences implements LoadFromCsv {
  private static Logger LOGGER = Logger.getLogger("InfoLogging");

  private CsvReader csvReader;
  private WorkExperiencesDao workExperiencesDao;
  private StudentsDao studentsDao;

  public LoadWorkExperiences() {
    workExperiencesDao = new WorkExperiencesDao();
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
        String companyName = csvReader.get("Company_Name").trim();
        Date startDate = dateFormat.parse(csvReader.get("Start_Date").trim());
        String start_Date = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
        Date endDate = dateFormat.parse(csvReader.get("End_Date").trim());
        String title = csvReader.get("Title").trim();
        String description = csvReader.get("Description").trim();
        boolean currentJob = csvReader.get("CurrentJob").trim().equals("Yes")?true:false;

        WorkExperiences experience = new WorkExperiences(neuId, companyName, startDate, endDate, currentJob, title, description);
        WorkExperiences existExperience = workExperiencesDao.getWorkExperience(neuId, companyName, startDate);

        if(!studentsDao.ifNuidExists(neuId)) {
          LOGGER.info("No student exists for " + neuId);
        } else if (existExperience!= null) {
          experience.setWorkExperienceId(existExperience.getWorkExperienceId());
          workExperiencesDao.updateWorkExperience(experience);
          LOGGER.info("Update work experience for " + neuId + " " + companyName + " " + startDate);
        } else {
          workExperiencesDao.createWorkExperience(experience);
          LOGGER.info("Add work experience for " + neuId + " " + companyName + " " + startDate);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } finally {
      csvReader.close();
      workExperiencesDao.getFactory().close();
      studentsDao.getFactory().close();
    }
  }
}
