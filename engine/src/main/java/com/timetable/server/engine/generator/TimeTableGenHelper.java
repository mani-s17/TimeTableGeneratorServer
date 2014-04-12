package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.input.SubjectStandard;
import java.util.HashMap;
import java.util.Map;

import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.SubjectVsTeacher;
import com.timetable.server.engine.model.common.TeacherView;
import com.timetable.server.engine.model.input.ClassGroup;
import com.timetable.server.engine.model.input.TeacherInfo;

public class TimeTableGenHelper {

	public static ClassView[] getClassViews(ClassGroup[] classGroups, int totalDays, int totalPeriods) {
		ClassView[] classViews = new ClassView[classGroups.length];
		int i = 0;
		for (ClassGroup classGroup : classGroups) {
			classViews[i++] = new ClassView(classGroup.getClassGroupId(), totalDays, totalPeriods);
		}

		return classViews;
	}

	public static String[] getClassIds(ClassGroup[] classGroups) {
		String[] classIds = new String[classGroups.length];
		int  i = 0;
		for (ClassGroup classGroup : classGroups) {
			classIds[i++] = classGroup.getClassGroupId();
		}
		return classIds;
	}

	public static TeacherView[] getTeacherViews(TeacherInfo[] teacherInfos, int totalDays, int totalPeriods) {
		TeacherView[] teacherViews = new TeacherView[teacherInfos.length];
		int i = 0;
		for (TeacherInfo teacherInfo : teacherInfos) {
			teacherViews[i++] = new TeacherView(teacherInfo.getTeacherId(), totalDays, totalPeriods);
		}

		return teacherViews;
	}

	public static Map<String, SubjectVsTeacher[]> getMapClassIdVsSubjectTeachers(TeacherInfo[] teacherInfos,
																		   ClassGroup[] classGroups) {
		// go over all the TeacherInfos to create the necessary information for each class..
		Map<Integer, Map<String, Integer>> standardVsMapSubjVsTchrId = new HashMap<>();
		for (TeacherInfo teacherInfo : teacherInfos) {
			int teacherId = teacherInfo.getTeacherId();
			SubjectStandard[] subjectStandards = teacherInfo.getSubjectStandards();
			for (SubjectStandard subjectStandard : subjectStandards) {
				Map<String, Integer> subjectIdVsTeacherId = standardVsMapSubjVsTchrId.get(subjectStandard.getStandard());
				if (subjectIdVsTeacherId == null) {
					subjectIdVsTeacherId = new HashMap<>();
					standardVsMapSubjVsTchrId.put(subjectStandard.getStandard(), subjectIdVsTeacherId);
				}
				// FIXME this place will possibly override previously stored teacher for the class.
				// and this is a problem.
				subjectIdVsTeacherId.put(subjectStandard.getSubject(), teacherId);
			}
		}

		Map<String, SubjectVsTeacher[]> classIdVsSubjectTeachers = new HashMap<>();

		for (ClassGroup classGroup : classGroups) {
			String classId = classGroup.getClassGroupId();
			int standardId = classGroup.getStandardId();
			SubjectVsTeacher[] subjectVsTeachers = new SubjectVsTeacher[classGroup.getSubjects().length];

			Map<String, Integer> subjVsTeacherId = standardVsMapSubjVsTchrId.get(standardId);

			int i = 0;
			for (String subject : classGroup.getSubjects()) {
				if (subjVsTeacherId.containsKey(subject))
					subjectVsTeachers[i++] = new SubjectVsTeacher(subject, subjVsTeacherId.get(subject));
			}

			classIdVsSubjectTeachers.put(classId, subjectVsTeachers);
		}

		return classIdVsSubjectTeachers;
	}

	// return the next_day and next_period.
	// all calculations are 0 based. (0 to n-1)
	public static int[] getNextDayPeriod(int day, int period, int totalDays, int totalPeriods) {
		// increment period
		int nextPeriod = ((period + 1) % totalPeriods);
		int nextDay = day;
		if (nextPeriod < period) { // if period overflows, goto next day
			nextDay = ((day + 1) % totalDays);
		}

		return new int[]{nextDay, nextPeriod};
	}

}
