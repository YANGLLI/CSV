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

  /**
   * Function to load database from csv.
   *
   * @param studentFile absolute student csv file path
   * @param courseFile absolute course csv file path
   * @param electiveFile absolute electives csv file path
   * @param workExperienceFile absolute work experience csv file path
   * @param priorEducationFile absolute prior education csv file path
   * @param test true for test database. false for product database.
   */
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
  }

  /**
   *   Main function. Arguments are the file Path for each csv file.
   *   java -jar CSV.jar [StudentFilePath] [CourseFilePath] [ElectiveFilePath] [WorkExperienceFilePath] [PriorEducationFilePath]
   */
  public static void main(String[] args) {
    String studentFile = args[0];
    String courseFile = args[1];
    String electiveFile = args[2];
    String workExperienceFile = args[3];
    String priorEducationFile = args[4];
    boolean test = false;
    if (args.length == 6) {
      test = Boolean.getBoolean(args[5]);
    }

//    String studentFile = "samples/Students.csv";
//    String courseFile = "samples/Courses.csv";
//    String electiveFile = "samples/Electives.csv";
//    String workExperienceFile = "samples/WorkExperiences.csv";
//    String priorEducationFile = "samples/PriorEducations.csv";

    loadAll(studentFile, courseFile, electiveFile, workExperienceFile, priorEducationFile, test);
  }
}
