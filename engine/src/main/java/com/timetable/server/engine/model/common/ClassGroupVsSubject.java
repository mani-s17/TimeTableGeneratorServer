package com.timetable.server.engine.model.common;

/**
 * class to be used by the teacher view of the time table output.
 * It would tell for a day and period, which class_group and which subject the teacher is scheduled
 * to teach.
 */
public class ClassGroupVsSubject {
	private String classGroupId;

	private String subjectId;

	public ClassGroupVsSubject(String classGroupId, String subjectId) {
		this.classGroupId = classGroupId;
		this.subjectId = subjectId;
	}

	public String getClassGroupId() {
		return classGroupId;
	}

	public String getSubjectId() {
		return subjectId;
	}
}
