package com.timetable.server.engine.model.input;

/**
 * TeacherInfo contains all the information of a teacher required for timetable scheduling.
 * viz. total teaching hours in the week, set of the subject-standard he/she can teach.
 */
public class TeacherInfo {

	private int teacherId;

	private int weeklyTeachingHours;

	private SubjectStandard[] subjectStandards;

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getWeeklyTeachingHours() {
		return weeklyTeachingHours;
	}

	public void setWeeklyTeachingHours(int weeklyTeachingHours) {
		this.weeklyTeachingHours = weeklyTeachingHours;
	}

	public SubjectStandard[] getSubjectStandards() {
		return subjectStandards;
	}

	public void setSubjectStandards(SubjectStandard[] subjectStandards) {
		this.subjectStandards = subjectStandards;
	}
}
