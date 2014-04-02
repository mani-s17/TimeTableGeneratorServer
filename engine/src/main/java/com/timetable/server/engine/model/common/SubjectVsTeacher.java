package com.timetable.server.engine.model.common;

/**
 * This class will be used by the class_view of the output.
 * Basically it will tell for a particular day and period, which is the scheduled subject and by
 * which teacher.
 */
public class SubjectVsTeacher {
	private int subjectId;

	private int teacherId;

	public SubjectVsTeacher(int subjectId, int teacherId) {
		this.subjectId = subjectId;
		this.teacherId = teacherId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public int getTeacherId() {
		return teacherId;
	}
}
