package com.timetable.server.engine.model.input;

/**
 * TimeTableInput represents the total information required to generate the weekly timetable.
 * It has the information of the teachers and the class groups (learners).
 */
public class TimeTableInput {
	private int totalPeriods;

	private TeacherInfo[] teacherInfos;

	private ClassGroup[] classGroups;

	public int getTotalPeriods() {
		return totalPeriods;
	}

	public void setTotalPeriods(int totalPeriods) {
		this.totalPeriods = totalPeriods;
	}

	public TeacherInfo[] getTeacherInfos() {
		return teacherInfos;
	}

	public void setTeacherInfos(TeacherInfo[] teacherInfos) {
		this.teacherInfos = teacherInfos;
	}

	public ClassGroup[] getClassGroups() {
		return classGroups;
	}

	public void setClassGroups(ClassGroup[] classGroups) {
		this.classGroups = classGroups;
	}
}
