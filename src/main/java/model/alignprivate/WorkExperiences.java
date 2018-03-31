package model.alignprivate;

import java.util.Date;

public class WorkExperiences {
  private int workExperienceId;
  private String neuId;
  private String companyName;
  private Date startDate;
  private Date endDate;
  private boolean currentJob;
  private boolean coop;
  private String title;
  private String description;

  public WorkExperiences(String neuId, String companyName, Date startDate, Date endDate,
                         boolean currentJob, boolean coop, String title, String description) {
    this.neuId = neuId;
    this.companyName = companyName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.currentJob = currentJob;
    this.coop = coop;
    this.title = title;
    this.description = description;
  }

  public WorkExperiences() { }

  public int getWorkExperienceId() {
    return workExperienceId;
  }

  public void setWorkExperienceId(int workExperienceId) {
    this.workExperienceId = workExperienceId;
  }

  public String getNeuId() {
    return neuId;
  }

  public void setNeuId(String neuId) {
    this.neuId = neuId;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public boolean isCurrentJob() {
    return currentJob;
  }

  public void setCurrentJob(boolean currentJob) {
    this.currentJob = currentJob;
  }

  public boolean isCoop() {
    return coop;
  }

  public void setCoop(boolean coop) {
    this.coop = coop;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "WorkExperiences{" +
            "neuId='" + neuId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", startDate=" + startDate +
            '}';
  }
}