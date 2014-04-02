package com.timetable.server.engine.model.common;

/**
 * class to be used by the teacher view of the time table output.
 * It would tell for a day and period, which class_group and which subject the teacher is scheduled
 * to teach.
 */
public class ClassGroupVsSubject {
	private int classGroupId;

	private int subjectId;

	public ClassGroupVsSubject(int classGroupId, int subjectId) {
		this.classGroupId = classGroupId;
		this.subjectId = subjectId;
	}

	public int getClassGroupId() {
		return classGroupId;
	}

	public int getSubjectId() {
		return subjectId;
	}
}
