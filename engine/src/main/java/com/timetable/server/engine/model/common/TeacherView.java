package com.timetable.server.engine.model.common;

/**
 * The teacher view of the time table output.
 *
 */
public class TeacherView {

	private int teacherId;

	private ClassGroupVsSubject[][] dayPeriodInfo;

	public TeacherView(int teacherId, int workDays, int totalPeriods) {
		this.teacherId = teacherId;
		dayPeriodInfo = new ClassGroupVsSubject[workDays][totalPeriods];
	}

	public int getTeacherId() {
		return teacherId;
	}

	public ClassGroupVsSubject getClassGroupVsSubject(int day, int period) {
		return dayPeriodInfo[day][period];
	}

	public void setClassGroupVsSubject(int day, int period, ClassGroupVsSubject classGroupVsSubject) {
		dayPeriodInfo[day][period] = classGroupVsSubject;
	}
}
