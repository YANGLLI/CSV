package model.alignprivate;

import enums.*;

import java.sql.Blob;

public class Students {
  private String neuId;
  private int publicId;
  private String email;
  private String firstName;
  private String middleName;
  private String lastName;
  private Gender gender;
//  private String race;
  private boolean scholarship;
  private String visa;
  private String phoneNum;
  private String address;
  private String state;
  private String city;
  private String zip;
  private Term entryTerm;
  private int entryYear;
  private Term expectedLastTerm;
  private int expectedLastYear;
  private EnrollmentStatus enrollmentStatus;
  private Campus campus;
  private DegreeCandidacy degree;
  private Blob photo;
  private boolean visible;
  private String linkedin;
  private String facebook;
  private String github;
  private String website;
  private String skills;
  private String summary;

  public Students(String neuId, String email, String firstName, String middleName, String lastName, Gender gender,
                  boolean scholarship, String visa, String phoneNum, String address, String state, String city, String zip,
                  Term entryTerm, int entryYear, Term expectedLastTerm, int expectedLastYear,
                  EnrollmentStatus status, Campus campus, DegreeCandidacy degree, Blob photo, boolean visible) {
    this.neuId = neuId;
    this.email = email;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.gender = gender;
//    this.race = race;
    this.scholarship = scholarship;
    this.visa = visa;
    this.phoneNum = phoneNum;
    this.address = address;
    this.state = state;
    this.city = city;
    this.zip = zip;
    this.entryTerm = entryTerm;
    this.entryYear = entryYear;
    this.expectedLastTerm = expectedLastTerm;
    this.expectedLastYear = expectedLastYear;
    this.enrollmentStatus = status;
    this.campus = campus;
    this.degree = degree;
    this.photo = photo;
    this.visible = visible;
  }

  public Students() {
    super();
  }

  public String getNeuId() {
    return neuId;
  }

  public void setNeuId(String neuId) {
    this.neuId = neuId;
  }

  public int getPublicId() {
    return publicId;
  }

  public void setPublicId(int publicId) {
    this.publicId = publicId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

//  public String getRace() {
//    return race;
//  }
//
//  public void setRace(String race) {
//    this.race = race;
//  }

  public boolean isScholarship() {
    return scholarship;
  }

  public void setScholarship(boolean scholarship) {
    this.scholarship = scholarship;
  }

  public String getVisa() {
    return visa;
  }

  public void setVisa(String visa) {
    this.visa = visa;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public Term getEntryTerm() {
    return entryTerm;
  }

  public void setEntryTerm(Term entryTerm) {
    this.entryTerm = entryTerm;
  }

  public int getEntryYear() {
    return entryYear;
  }

  public void setEntryYear(int entryYear) {
    this.entryYear = entryYear;
  }

  public Term getExpectedLastTerm() {
    return expectedLastTerm;
  }

  public void setExpectedLastTerm(Term expectedLastTerm) {
    this.expectedLastTerm = expectedLastTerm;
  }

  public int getExpectedLastYear() {
    return expectedLastYear;
  }

  public void setExpectedLastYear(int expectedLastYear) {
    this.expectedLastYear = expectedLastYear;
  }

  public EnrollmentStatus getEnrollmentStatus() {
    return enrollmentStatus;
  }

  public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
    this.enrollmentStatus = enrollmentStatus;
  }

  public Campus getCampus() {
    return campus;
  }

  public void setCampus(Campus campus) {
    this.campus = campus;
  }

  public DegreeCandidacy getDegree() {
    return degree;
  }

  public void setDegree(DegreeCandidacy degree) {
    this.degree = degree;
  }

  public Blob getPhoto() {
    return photo;
  }

  public void setPhoto(Blob photo) {
    this.photo = photo;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public String getLinkedin() {
    return linkedin;
  }

  public void setLinkedin(String linkedin) {
    this.linkedin = linkedin;
  }

  public String getFacebook() {
    return facebook;
  }

  public void setFacebook(String facebook) {
    this.facebook = facebook;
  }

  public String getGithub() {
    return github;
  }

  public void setGithub(String github) {
    this.github = github;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public String getSkills() {
    return skills;
  }

  public void setSkills(String skills) {
    this.skills = skills;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  @Override
  public String toString() {
    return "StudentsPublic{" +
            "neuId='" + neuId + '\'' +
            '}';
  }
}