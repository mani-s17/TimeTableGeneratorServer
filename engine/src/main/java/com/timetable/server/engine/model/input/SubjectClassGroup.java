package com.timetable.server.engine.model.input;

/**
 * A teacher can teach a subject to a particular classGroup. This class represents this combination.
 */
public class SubjectClassGroup {

	private String subject;

	private String classGroup;

	public SubjectClassGroup() {};

	public SubjectClassGroup(String subject, String classGroup) {
		this.subject = subject;
		this.classGroup = classGroup;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getClassGroup() {
		return classGroup;
	}

	public void setClassGroup(String classGroup) {
		this.classGroup = classGroup;
	}

	@Override
	public String toString() {
		return "SubjectClassGroup {" +
				"subject='" + subject + '\'' +
				", classGroup='" + classGroup + '\'' +
				'}';
	}
}
