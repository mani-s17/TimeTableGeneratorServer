package com.timetable.server.engine.model.common;

import com.timetable.server.engine.model.input.ClassGroup;
import java.util.HashMap;
import java.util.Map;

/**
 * The individual class groups view of the time table output.
 * On particular day and particular period, this will tell which teacher is teaching which subject.
 */
public class ClassView {
	private int workDays;
	private int totalPeriods;

	private SubjectVsTeacher[][] dayPeriodInfo;

	private ClassGroup classGroup;

	private Map<String, Integer> subjectVsHoursConsumed;

	public ClassView(ClassGroup classGroup, int workDays, int totalPeriods) {
		this.classGroup = classGroup;
		this.workDays = workDays;
		this.totalPeriods = totalPeriods;
		dayPeriodInfo = new SubjectVsTeacher[workDays][totalPeriods];
		initSubjectVsHoursConsumed();
	}

	private void initSubjectVsHoursConsumed() {
		subjectVsHoursConsumed = new HashMap<>();
		for (String subject : classGroup.getSubjects()) {
			subjectVsHoursConsumed.put(subject, 0);
		}
	}

	public String getClassGroupId() {
		return classGroup.getClassGroupId();
	}

	public ClassGroup getClassGroup() {
		return classGroup;
	}

	public Integer getExpectedTeachingHours(String subject) {
		Integer expectedHours = classGroup.getSubjectVsHourMap().get(subject);
		if (expectedHours == null)
			expectedHours = 0;
		return expectedHours;
	}

	public Integer getActualTeachingHours(String subject) {
		Integer actualTeachingHours = subjectVsHoursConsumed.get(subject);
		if (actualTeachingHours == null)
			actualTeachingHours = 0;
		return actualTeachingHours;
	}

	public SubjectVsTeacher getSubjectVsTeacher(int day, int period) {
		return dayPeriodInfo[day][period];
	}

	public void setSubjectVsTeacher(int day, int period, SubjectVsTeacher subjectVsTeacher) {
		updateTeachingHours(day, period, subjectVsTeacher);
		dayPeriodInfo[day][period] = subjectVsTeacher;
	}

	private void updateTeachingHours(int day, int period, SubjectVsTeacher subjectVsTeacher) {
		if (subjectVsTeacher != null) {
			updateTeachingHourCount(subjectVsTeacher.getSubjectId());
		}
		else {
			SubjectVsTeacher oldSubjectTeacher = dayPeriodInfo[day][period];
			String oldSubject = oldSubjectTeacher != null ? oldSubjectTeacher.getSubjectId() : null;
			resetTeachingHourCount(oldSubject);
		}
	}

	private void updateTeachingHourCount(String newSubject) {
		Integer count = subjectVsHoursConsumed.get(newSubject);
		count++;
		subjectVsHoursConsumed.put(newSubject, count);
	}

	private void resetTeachingHourCount(String oldSubject) {
		if (oldSubject == null)
			return;
		Integer count = subjectVsHoursConsumed.get(oldSubject);
		count--;
		subjectVsHoursConsumed.put(oldSubject, count);
	}

	@Override
	public String toString() {
		return "ClassView: " +
				"classGroupId=" + classGroup.getClassGroupId() + '\n' +
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
