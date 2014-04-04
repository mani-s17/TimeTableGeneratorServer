package com.timetable.server.engine.model.common;

/**
 * The individual class groups view of the time table output.
 */
public class ClassView {

	private int classGroupId;

	private int workDays;

	private int totalPeriods;

	private SubjectVsTeacher[][] dayPeriodInfo;

	public ClassView(int classGroupId,int workDays, int totalPeriods) {
		this.classGroupId = classGroupId;
		this.workDays = workDays;
		this.totalPeriods = totalPeriods;
		dayPeriodInfo = new SubjectVsTeacher[workDays][totalPeriods];
	}

	public int getClassGroupId() {
		return classGroupId;
	}

	public SubjectVsTeacher getSubjectVsTeacher(int day, int period) {
		return dayPeriodInfo[day][period];
	}

	public void setSubjectVsTeacher(int day, int period, SubjectVsTeacher subjectVsTeacher) {
		dayPeriodInfo[day][period] = subjectVsTeacher;
	}
}
