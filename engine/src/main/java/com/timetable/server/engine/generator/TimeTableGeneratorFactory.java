package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.TeacherView;
import com.timetable.server.engine.model.input.ClassGroup;
import com.timetable.server.engine.model.input.TeacherInfo;
import com.timetable.server.engine.model.input.TimeTableInput;

public class TimeTableGeneratorFactory {

	public TimeTableGenerator getBruteForceGenerator(TimeTableInput timeTableInput) {

		int totalDays = timeTableInput.getTotalDays();
		int totalClasses = timeTableInput.getTotalClasses();
		int totalPeriods = timeTableInput.getTotalPeriods();
		int totalTeachers = timeTableInput.getTotalTeachers();

		TeacherInfo[] teacherInfos = timeTableInput.getTeacherInfos();

		ClassGroup[] classGroups = timeTableInput.getClassGroups();

		ClassView[] classViews = null;
		TeacherView[] teacherViews = null;

//		DomainStore domainStore = new DomainStore(teacherInfos, classViews, teacherViews);

		return null;
	}

	private ClassView[] getClassViews(ClassGroup[] classGroups, int totalDays, int totalPeriods) {
		ClassView[] classViews = new ClassView[classGroups.length];
		int i = 0;
		for (ClassGroup classGroup : classGroups) {
			classViews[i++] = new ClassView(classGroup.getClassStandard(), totalDays, totalPeriods);
		}

		return classViews;
	}

	private TeacherView[] getTeacherViews(TeacherInfo[] teacherInfos, int totalDays, int totalPeriods) {
		TeacherView[] teacherViews = new TeacherView[teacherInfos.length];
		int i = 0;
		for (TeacherInfo teacherInfo : teacherInfos) {
			teacherViews[i++] = new TeacherView(teacherInfo.getTeacherId(), totalDays, totalPeriods);
		}

		return teacherViews;
	}
}
