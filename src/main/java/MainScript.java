import dao.alignadmin.AdminSessionFactory;
import dao.alignadmin.AdminTestSessionFactory;
import dao.alignprivate.StudentSessionFactory;
import dao.alignprivate.StudentTestSessionFactory;
import dao.alignpublic.PublicSessionFactory;
import dao.alignpublic.PublicTestSessionFactory;
import extractToPublic.AggregatedDataLoader;
import loadRecords.*;

public class MainScript {
  public AggregatedDataLoader aggregatedDataLoader;

  public LoadFromCsv loader;

  public LoadFromCsv getLoadcsv() {
    return loader;
  }

  public AggregatedDataLoader getAggregatedDataLoader() {
    return aggregatedDataLoader;
  }

  public void setAggregatedDataLoader(AggregatedDataLoader aggregatedDataLoader) {
    this.aggregatedDataLoader = aggregatedDataLoader;
  }

  public void setLoadcsv(LoadFromCsv loader) {
    this.loader = loader;
  }

  public static void loadAll(String studentFile, String courseFile, String electiveFile, String workExperienceFile,
                             String priorEducationFile, boolean test) {


    MainScript script = new MainScript();

    script.setLoadcsv(new LoadStudents(test));
    script.getLoadcsv().loadDatabase(studentFile);

    script.setLoadcsv(new LoadCourses(test));
    script.getLoadcsv().loadDatabase(courseFile);

    script.setLoadcsv(new LoadElectives(test));
    script.getLoadcsv().loadDatabase(electiveFile);

    script.setLoadcsv(new LoadWorkExperiences(test));
    script.getLoadcsv().loadDatabase(workExperienceFile);

    script.setLoadcsv(new LoadPriorEducations(test));
    script.getLoadcsv().loadDatabase(priorEducationFile);

    script.setAggregatedDataLoader(new AggregatedDataLoader(test));
    script.getAggregatedDataLoader().extractAndLoad();

//    if (test) {
//      StudentTestSessionFactory.getFactory().close();
//      PublicTestSessionFactory.getFactory().close();
//      AdminTestSessionFactory.getFactory().close();
//    } else {
//      StudentSessionFactory.getFactory().close();
//      PublicSessionFactory.getFactory().close();
//      AdminSessionFactory.getFactory().close();
//    }
  }

  /**
   *   1. Specify the file Path for each csv file.
   *   2. Run main function to load database.
   *   Note: If you updated in database schema, you also need to update models and
   *   hibernate mappings.
   */
  public static void main(String[] args) {
    String studentFile = "/Users/yang/Courses/ASD/Backend/CSV/samples/Students.csv";
    String courseFile = "/Users/yang/Courses/ASD/Backend/CSV/samples/Courses.csv";
    String electiveFile = "/Users/yang/Courses/ASD/Backend/CSV/samples/Electives.csv";
    String workExperienceFile = "/Users/yang/Courses/ASD/Backend/CSV/samples/WorkExperiences.csv";
    String priorEducationFile = "/Users/yang/Courses/ASD/Backend/CSV/samples/PriorEducations.csv";

    loadAll(studentFile, courseFile, electiveFile, workExperienceFile, priorEducationFile, false);
  }
}
