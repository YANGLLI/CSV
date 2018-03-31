import loadRecords.*;

public class MainScript {
  public LoadFromCsv loader;

  public LoadFromCsv getLoadcsv() {
    return loader;
  }

  public void setLoadcsv(LoadFromCsv loader) {
    this.loader = loader;
  }

  /**
   *   1. Specify the file Path for each csv file.
   *   2. Run main function to load database.
   *   Note: If you updated in database schema, you also need to update models and
   *   hibernate mappings.
   */
  public static void main(String[] args) {
    MainScript script = new MainScript();

    script.setLoadcsv(new LoadCourses());
    script.getLoadcsv().loadDatabase("/Users/yang/Courses/ASD/Backend/Courses.csv");

    script.setLoadcsv(new LoadStudents());
    script.getLoadcsv().loadDatabase("/Users/yang/Courses/ASD/Backend/Students.csv");

    script.setLoadcsv(new LoadElectives());
    script.getLoadcsv().loadDatabase("/Users/yang/Courses/ASD/Backend/Electives.csv");

    script.setLoadcsv(new LoadWorkExperiences());
    script.getLoadcsv().loadDatabase("/Users/yang/Courses/ASD/Backend/WorkExperiences.csv");

    script.setLoadcsv(new LoadPriorEducations());
    script.getLoadcsv().loadDatabase("/Users/yang/Courses/ASD/Backend/PriorEducations.csv");

  }
}
