package model.alignpublic;

public class WorkExperiencesPublic {
  private int workExperienceId;
  private int publicId;
  private String coop;

  public WorkExperiencesPublic(int workExperienceId, int publicId, String coop) {
    this.workExperienceId = workExperienceId;
    this.publicId = publicId;
    this.coop = coop;
  }

  public WorkExperiencesPublic() {

  }

  public int getWorkExperienceId() {
    return workExperienceId;
  }

  public void setWorkExperienceId(int workExperienceId) {
    this.workExperienceId = workExperienceId;
  }

  public int getPublicId() {
    return publicId;
  }

  public void setPublicId(int publicId) {
    this.publicId = publicId;
  }

  public String getCoop() {
    return coop;
  }

  public void setCoop(String coop) {
    this.coop = coop;
  }
}
