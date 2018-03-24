package model;

public class Courses {
	private String courseId;
	private String courseName;
	private String description;

	public Courses (String courseId, String courseName, String description) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.description = description;
	}

	public Courses () { }

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Courses{" +
				"courseId='" + courseId + '\'' +
				", courseName='" + courseName + '\'' +
				", courseDescription='" + description + '\'' +
				'}';
	}
}