package com.timetable.server.engine.model.input;

import java.util.Map;

/**
 * A class group is a combination of students (of a standard) who study a set of common subjects.
 *
 */
// TODO should we not fix a set of teachers for a particular class group?? (that is it should be given as a
//	part of the input.
public class ClassGroup {

	private String classGroupId;

	private int StandardId;

	private int totalStudents;

	private String[] subjects;

	private Map<String, Integer> subjectVsHourMap;

	public String getClassGroupId() {
		return classGroupId;
	}

	public void setClassGroupId(String classGroupId) {
		this.classGroupId = classGroupId;
	}

	public int getStandardId() {
		return StandardId;
	}

	public void setStandardId(int standardId) {
		StandardId = standardId;
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

	public Map<String, Integer> getSubjectVsHourMap() {
		return subjectVsHourMap;
	}

	public void setSubjectVsHourMap(Map<String, Integer> subjectVsHourMap) {
		this.subjectVsHourMap = subjectVsHourMap;
	}

}
