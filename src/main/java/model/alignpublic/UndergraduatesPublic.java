package model.alignpublic;

public class UndergraduatesPublic {
  private int undergraduateId;
  private int publicId;
  private String undergradDegree;
  private String undergradSchool;

  public UndergraduatesPublic(int undergraduateId, int publicId, String undergradDegree, String undergradSchool) {
    this.undergraduateId = undergraduateId;
    this.publicId = publicId;
    this.undergradDegree = undergradDegree;
    this.undergradSchool = undergradSchool;
  }

  public UndergraduatesPublic() {

  }

  public int getUndergraduateId() {
    return undergraduateId;
  }

  public void setUndergraduateId(int undergraduateId) {
    this.undergraduateId = undergraduateId;
  }

  public int getPublicId() {
    return publicId;
  }

  public void setPublicId(int publicId) {
    this.publicId = publicId;
  }

  public String getUndergradDegree() {
    return undergradDegree;
  }

  public void setUndergradDegree(String undergradDegree) {
    this.undergradDegree = undergradDegree;
  }

  public String getUndergradSchool() {
    return undergradSchool;
  }

  public void setUndergradSchool(String undergradSchool) {
    this.undergradSchool = undergradSchool;
  }
}
