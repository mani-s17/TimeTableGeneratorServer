package com.timetable.server.engine.model.common;

/**
 * The teacher view of the time table output.
 *
 */
public class TeacherView {

	private int workDays;
	private int totalPeriods;

	private ClassGroupVsSubject[][] dayPeriodInfo;

	public TeacherView(int workDays, int totalPeriods) {
		this.workDays = workDays;
		this.totalPeriods = totalPeriods;
		dayPeriodInfo = new ClassGroupVsSubject[workDays][totalPeriods];
	}

	public ClassGroupVsSubject getClassGroupVsSubject(int day, int period) {
		return dayPeriodInfo[day][period];
	}

	public void setClassGroupVsSubject(int day, int period, ClassGroupVsSubject classGroupVsSubject) {
		dayPeriodInfo[day][period] = classGroupVsSubject;
	}
}
