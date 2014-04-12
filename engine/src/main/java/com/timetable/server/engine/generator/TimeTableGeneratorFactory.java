package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.SubjectVsTeacher;
import com.timetable.server.engine.model.common.TeacherView;
import com.timetable.server.engine.model.input.ClassGroup;
import com.timetable.server.engine.model.input.TeacherInfo;
import com.timetable.server.engine.model.input.TimeTableInput;
import java.util.Map;

public class TimeTableGeneratorFactory {

	public TimeTableGenerator getBruteForceGenerator(TimeTableInput timeTableInput) {

		int totalDays = timeTableInput.getTotalDays();
		int totalClasses = timeTableInput.getTotalClasses();
		int totalPeriods = timeTableInput.getTotalPeriods();
		int totalTeachers = timeTableInput.getTotalTeachers();

		TeacherInfo[] teacherInfos = timeTableInput.getTeacherInfos();

		ClassGroup[] classGroups = timeTableInput.getClassGroups();
		String[] classIds = TimeTableGenHelper.getClassIds(classGroups);

		ClassView[] classViews = TimeTableGenHelper.getClassViews(classGroups, totalDays, totalPeriods);
		TeacherView[] teacherViews = TimeTableGenHelper.getTeacherViews(teacherInfos, totalDays, totalPeriods);

		DomainStore domainStore = new DomainStore(teacherInfos, classViews, teacherViews);

		Map<String, SubjectVsTeacher[]> classIdVsSubjectTeachers = TimeTableGenHelper.getMapClassIdVsSubjectTeachers(
																							teacherInfos, classGroups);

		return new BruteForceGenerator(totalDays, totalClasses, totalPeriods, totalTeachers, classIds,
										domainStore, classIdVsSubjectTeachers);
	}
}
