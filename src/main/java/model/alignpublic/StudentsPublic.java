package model.alignpublic;

public class StudentsPublic {
  private int publicId;
  private int graduationYear;
  private boolean visibleToPublic;

  public StudentsPublic(int publicId, int graduationYear, boolean visibleToPublic) {
    this.publicId = publicId;
    this.graduationYear = graduationYear;
    this.visibleToPublic = visibleToPublic;
  }

  public StudentsPublic() {

  }

  public int getPublicId() {
    return publicId;
  }

  public void setPublicId(int publicId) {
    this.publicId = publicId;
  }

  public int getGraduationYear() {
    return graduationYear;
  }

  public void setGraduationYear(int graduationYear) {
    this.graduationYear = graduationYear;
  }

  public boolean isVisibleToPublic() {
    return visibleToPublic;
  }

  public void setVisibleToPublic(boolean visibleToPublic) {
    this.visibleToPublic = visibleToPublic;
  }
}
