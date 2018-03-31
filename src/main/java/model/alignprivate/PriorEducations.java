package model.alignprivate;

import enums.DegreeCandidacy;

import java.util.Date;

public class PriorEducations {
  private int priorEducationId;
  private String neuId;
  private String institutionName;
  private String majorName;
  private Date graduationDate;
  private float gpa;
  private DegreeCandidacy degreeCandidacy;

  public PriorEducations(String neuId, String institutionName, String majorName, Date graduationDate,
                         float gpa, DegreeCandidacy degreeCandidacy) {
    this.institutionName = institutionName;
    this.majorName = majorName;
    this.graduationDate = graduationDate;
    this.gpa = gpa;
    this.degreeCandidacy = degreeCandidacy;
    this.neuId = neuId;
  }

  public PriorEducations() { }

  public int getPriorEducationId() {
    return priorEducationId;
  }

  public void setPriorEducationId(int priorEducationId) {
    this.priorEducationId = priorEducationId;
  }

  public String getInstitutionName() {
    return institutionName;
  }

  public void setInstitutionName(String institutionName) {
    this.institutionName = institutionName;
  }

  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public Date getGraduationDate() {
    return graduationDate;
  }

  public void setGraduationDate(Date graduationDate) {
    this.graduationDate = graduationDate;
  }

  public float getGpa() {
    return gpa;
  }

  public void setGpa(float gpa) {
    this.gpa = gpa;
  }

  public DegreeCandidacy getDegreeCandidacy() {
    return degreeCandidacy;
  }

  public void setDegreeCandidacy(DegreeCandidacy degreeCandidacy) {
    this.degreeCandidacy = degreeCandidacy;
  }

  public String getNeuId() {
    return neuId;
  }

  public void setNeuId(String neuId) {
    this.neuId = neuId;
  }

  @Override
  public String toString() {
    return "PriorEducations{" +
            "neuId='" + neuId + '\'' +
            ", institutionName='" + institutionName + '\'' +
            ", majorName='" + majorName + '\'' +
            ", degree='" + degreeCandidacy + '\'' +
            '}';
  }
}
