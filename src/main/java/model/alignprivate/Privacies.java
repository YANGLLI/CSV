package model.alignprivate;

public class Privacies {
  private String neuId;
  private int publicId;
  private boolean visibleToPublic;
  private boolean photo;
  private boolean coop;
  private boolean phone;
  private boolean email;
  private boolean address;
  private boolean linkedin;
  private boolean github;
  private boolean facebook;
  private boolean website;
  private boolean course;
  private boolean extraExperience;
  private boolean project;
  private boolean skill;

  public Privacies() { }

  public Privacies(String neuId, int publicId, boolean visibleToPublic, boolean photo, boolean coop, boolean phone, boolean email,
                   boolean address, boolean linkedin, boolean github, boolean facebook, boolean website, boolean course,
                   boolean extraExperience, boolean project, boolean skill) {
    this.neuId = neuId;
    this.publicId = publicId;
    this.visibleToPublic = visibleToPublic;
    this.photo = photo;
    this.coop = coop;
    this.phone = phone;
    this.email = email;
    this.address = address;
    this.linkedin = linkedin;
    this.github = github;
    this.facebook = facebook;
    this.website = website;
    this.course = course;
    this.extraExperience = extraExperience;
    this.project = project;
    this.skill = skill;
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

  public boolean isVisibleToPublic() {
    return visibleToPublic;
  }

  public void setVisibleToPublic(boolean visibleToPublic) {
    this.visibleToPublic = visibleToPublic;
  }

  public boolean isPhoto() {
    return photo;
  }

  public void setPhoto(boolean photo) {
    this.photo = photo;
  }

  public boolean isCoop() {
    return coop;
  }

  public void setCoop(boolean coop) {
    this.coop = coop;
  }

  public boolean isEmail() {
    return email;
  }

  public boolean isPhone() {
    return phone;
  }

  public void setPhone(boolean phone) {
    this.phone = phone;
  }

  public void setEmail(boolean email) {
    this.email = email;
  }

  public boolean isAddress() {
    return address;
  }

  public void setAddress(boolean address) {
    this.address = address;
  }

  public boolean isLinkedin() {
    return linkedin;
  }

  public void setLinkedin(boolean linkedin) {
    this.linkedin = linkedin;
  }

  public boolean isGithub() {
    return github;
  }

  public void setGithub(boolean github) {
    this.github = github;
  }

  public boolean isFacebook() {
    return facebook;
  }

  public void setFacebook(boolean facebook) {
    this.facebook = facebook;
  }

  public boolean isWebsite() {
    return website;
  }

  public void setWebsite(boolean website) {
    this.website = website;
  }

  public boolean isCourse() {
    return course;
  }

  public void setCourse(boolean course) {
    this.course = course;
  }

  public boolean isExtraExperience() {
    return extraExperience;
  }

  public void setExtraExperience(boolean extraExperience) {
    this.extraExperience = extraExperience;
  }

  public boolean isProject() {
    return project;
  }

  public void setProject(boolean project) {
    this.project = project;
  }

  public boolean isSkill() {
    return skill;
  }

  public void setSkill(boolean skill) {
    this.skill = skill;
  }

  @Override
  public String toString() {
    return "Privacies{" +
            "neuId='" + neuId + '\'' +
            '}';
  }
}

