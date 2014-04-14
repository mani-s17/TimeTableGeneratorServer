package com.timetable.server.engine.generator;

import com.timetable.server.engine.generator.constraints.CheckConstraints;
import com.timetable.server.engine.generator.constraints.ConstraintChecker;
import com.timetable.server.engine.generator.constraints.SubjectHourConstChecker;
import com.timetable.server.engine.generator.constraints.TeacherPeriodsConstChecker;
import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.SubjectVsTeacher;
import com.timetable.server.engine.model.common.TeacherView;
import com.timetable.server.engine.model.input.ClassGroup;
import com.timetable.server.engine.model.input.TeacherInfo;
import com.timetable.server.engine.model.input.TimeTableInput;
import java.util.ArrayList;
import java.util.List;
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

		CheckConstraints checkConstraints = new CheckConstraints(getConstraintCheckers(domainStore));

		return new BruteForceGenerator(totalDays, totalClasses, totalPeriods, totalTeachers, classIds,
										domainStore, classIdVsSubjectTeachers, checkConstraints);
	}

	private List<ConstraintChecker> getConstraintCheckers(DomainStore domainStore) {
		List<ConstraintChecker> constraintCheckers = new ArrayList<>();
//		constraintCheckers.add(new TeacherPeriodsConstChecker(domainStore));
		constraintCheckers.add(new SubjectHourConstChecker(domainStore));
		return constraintCheckers;
	}
}
