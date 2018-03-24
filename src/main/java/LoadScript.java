import loadEntity.LoadFromCsv;
import loadEntity.LoadStudents;

public class LoadScript {
  public LoadFromCsv loader;

  public LoadFromCsv getLoadcsv() {
    return loader;
  }

  public void setLoadcsv(LoadFromCsv loader) {
    this.loader = loader;
  }

  public static void main(String[] args) {
    LoadScript script = new LoadScript();

    script.setLoadcsv(new LoadStudents());
    script.getLoadcsv().loadDatabase("/Users/yang/Courses/ASD/Backend/Students.csv");
  }
}
