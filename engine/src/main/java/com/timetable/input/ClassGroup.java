package com.timetable.input;

/**
 * A class group is a combination of students (of a standard) who study a set of common subjects.
 *
 */
public class ClassGroup {

	private int classStandard;

	private int totalStudents;

	private String[] subjects;

	public int getClassStandard() {
		return classStandard;
	}

	public void setClassStandard(int classStandard) {
		this.classStandard = classStandard;
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
