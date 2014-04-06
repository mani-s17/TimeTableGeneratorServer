package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.SubjectVsTeacher;
import com.timetable.server.engine.model.common.TeacherView;
import com.timetable.server.engine.model.input.ClassGroup;
import com.timetable.server.engine.model.input.SubjectClassGroup;
import com.timetable.server.engine.model.input.TeacherInfo;
import java.util.HashMap;
import java.util.Map;

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
		Map<String, Map<String, Integer>> classIdVsMapSubjVsTchrId = new HashMap<>();
		for (TeacherInfo teacherInfo : teacherInfos) {
			int teacherId = teacherInfo.getTeacherId();
			SubjectClassGroup[] subjectClassGroups = teacherInfo.getSubjectClassGroups();
			for (SubjectClassGroup subjectClassGroup : subjectClassGroups) {
				Map<String, Integer> subjectIdVsTeacherId = classIdVsMapSubjVsTchrId.get(subjectClassGroup.getClassGroup());
				if (subjectIdVsTeacherId == null) {
					subjectIdVsTeacherId = new HashMap<>();
					classIdVsMapSubjVsTchrId.put(subjectClassGroup.getClassGroup(), subjectIdVsTeacherId);
				}
				// FIXME this place will possibly override previously stored teacher for the class.
				// and this is a problem.
				subjectIdVsTeacherId.put(subjectClassGroup.getSubject(), teacherId);
			}
		}

		Map<String, SubjectVsTeacher[]> classIdVsSubjectTeachers = new HashMap<>();

		for (ClassGroup classGroup : classGroups) {
			String classId = classGroup.getClassGroupId();
			SubjectVsTeacher[] subjectVsTeachers = new SubjectVsTeacher[classGroup.getSubjects().length];

			Map<String, Integer> subjVsTeacherId = classIdVsMapSubjVsTchrId.get(classId);

			int i = 0;
			for (String subject : classGroup.getSubjects()) {
				subjectVsTeachers[i++] = new SubjectVsTeacher(subject, subjVsTeacherId.get(subject));
			}

			classIdVsSubjectTeachers.put(classId, subjectVsTeachers);
		}

		return classIdVsSubjectTeachers;
	}

}
