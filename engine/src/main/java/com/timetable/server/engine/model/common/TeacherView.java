package com.timetable.server.engine.model.common;

import java.util.Arrays;

/**
 * The teacher view of the time table output.
 *
 */
public class TeacherView {

	private int teacherId;

	private int workDays;
	private int totalPeriods;

	private ClassGroupVsSubject[][] dayPeriodInfo;

	public TeacherView(int teacherId, int workDays, int totalPeriods) {
		this.teacherId = teacherId;
		this.workDays = workDays;
		this.totalPeriods = totalPeriods;
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

	@Override
	public String toString() {
		return "TeacherView: " +
				"teacherId = " + teacherId + "\n" +
				dayPeriodInfoToString();
	}

	private String dayPeriodInfoToString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t\t");
		for (int i = 0; i < totalPeriods; i++) {
			stringBuilder.append("PERIOD " + (i + 1));
			stringBuilder.append("\t\t");
		}
		stringBuilder.append("\n");

		for (int i = 0; i < workDays; i++) {
			stringBuilder.append("DAY " + (i + 1) + ": ");
			for (int j = 0; j < totalPeriods; j++) {
				if (dayPeriodInfo[i][j] == null)
					continue;
				stringBuilder.append(dayPeriodInfo[i][j].getClassGroupId() + "/" + dayPeriodInfo[i][j].getSubjectId());
				stringBuilder.append("\t\t");
			}
			stringBuilder.append("\n");
		}

		return stringBuilder.toString();
	}
}
