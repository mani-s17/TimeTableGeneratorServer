package com.timetable.server.engine.model.input;

/**
 * A class group is a combination of students (of a standard) who study a set of common subjects.
 *
 */
// TODO should we not fix a set of teachers for a particular class group?? (that is it should be given as a
//	part of the input.
public class ClassGroup {

	private String classGroupId;

	private int totalStudents;

	private String[] subjects;

	public String getClassGroupId() {
		return classGroupId;
	}

	public void setClassGroupId(String classGroupId) {
		this.classGroupId = classGroupId;
	}

	public int getTotalStudents() {
		return totalStudents;
	}

	public void setTotalStudents(int totalStudents) {
		this.totalStudents = totalStudents;
	}

	public String[] getSubjects() {
		return subjects;
	}

	public void setSubjects(String[] subjects) {
		this.subjects = subjects;
	}
}
