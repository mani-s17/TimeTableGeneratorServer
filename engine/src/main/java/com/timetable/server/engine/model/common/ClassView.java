package com.timetable.server.engine.model.common;

import java.util.Arrays;

/**
 * The individual class groups view of the time table output.
 * On particular day and particular period, this will tell which teacher is teaching which subject.
 */
public class ClassView {

	private String classGroupId;

	private int workDays;
	private int totalPeriods;

	private SubjectVsTeacher[][] dayPeriodInfo;

	public ClassView(String classGroupId,int workDays, int totalPeriods) {
		this.classGroupId = classGroupId;
		this.workDays = workDays;
		this.totalPeriods = totalPeriods;
		dayPeriodInfo = new SubjectVsTeacher[workDays][totalPeriods];
	}

	public String getClassGroupId() {
		return classGroupId;
	}

	public SubjectVsTeacher getSubjectVsTeacher(int day, int period) {
		return dayPeriodInfo[day][period];
	}

	public void setSubjectVsTeacher(int day, int period, SubjectVsTeacher subjectVsTeacher) {
		dayPeriodInfo[day][period] = subjectVsTeacher;
	}

	@Override
	public String toString() {
		return "ClassView: " +
				"classGroupId=" + classGroupId + '\n' +
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
				stringBuilder.append(dayPeriodInfo[i][j].getTeacherId() + "/" + dayPeriodInfo[i][j].getSubjectId());
				stringBuilder.append("\t\t");
			}
			stringBuilder.append("\n");
		}

		return stringBuilder.toString();
	}
}
