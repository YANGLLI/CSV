package model.alignprivate;

import enums.Term;

public class Electives {
  private int electiveId;

  private String neuId;
  private String courseId;
  private String courseName;
  private Term courseTerm;
  private int courseYear;

  public Electives(String neuId, String courseId, String courseName, Term courseTerm, int courseYear) {
    this.neuId = neuId;
    this.courseId = courseId;
    this.courseName = courseName;
    this.courseTerm = courseTerm;
    this.courseYear = courseYear;
  }

  public Electives() { }

  public int getElectiveId() {
    return electiveId;
  }

  public void setElectiveId(int electiveId) {
    this.electiveId = electiveId;
  }

  public String getNeuId() {
    return neuId;
  }

  public void setNeuId(String neuId) {
    this.neuId = neuId;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public Term getCourseTerm() {
    return courseTerm;
  }

  public void setCourseTerm(Term courseTerm) {
    this.courseTerm = courseTerm;
  }

  public int getCourseYear() {
    return courseYear;
  }

  public void setCourseYear(int courseYear) {
    this.courseYear = courseYear;
  }

  @Override
  public String toString() {
    return "Electives{" +
            "neuId='" + neuId + '\'' +
            ", courseId='" + courseId + '\'' +
            ", courseTerm=" + courseTerm +
            ", courseYear=" + courseYear +
            '}';
  }
}