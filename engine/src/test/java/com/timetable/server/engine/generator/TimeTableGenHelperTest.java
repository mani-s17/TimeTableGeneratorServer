package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.SubjectVsTeacher;
import com.timetable.server.engine.model.common.TeacherView;
import com.timetable.server.engine.model.input.ClassGroup;
import com.timetable.server.engine.model.input.SubjectClassGroup;
import com.timetable.server.engine.model.input.TeacherInfo;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TimeTableGenHelperTest {

	@Test
	public void testGetClassViews() {
		int totalDays = 2;
		int totalPeriods = 4;

		ClassGroup[] classGroups = new ClassGroup[2];
		classGroups[0] = getClassGroup1();
		classGroups[1] = getClassGroup2();

		ClassView[] classViews = TimeTableGenHelper.getClassViews(classGroups, totalDays, totalPeriods);
		assertEquals("size of returned array is not proper", classViews.length, 2);

		for (int i = 0; i < 2; i++) {
			ClassView classView = classViews[i];
			ClassGroup classGroup = classGroups[i];
			assertEquals("class id is not same", classView.getClassGroupId(), classGroup.getClassGroupId());
		}
	}

	@Test
	public void testClassIdsIsProper() {
		ClassGroup[] classGroups = new ClassGroup[2];
		classGroups[0] = getClassGroup1();
		classGroups[1] = getClassGroup2();

		String[] classIds = TimeTableGenHelper.getClassIds(classGroups);

		for (int i = 0; i < 2; i++) {
			assertEquals("class id is different", classIds[i], classGroups[i].getClassGroupId());
		}
	}

	@Test
	public void testGetTeacherViewsIsProper() {
		int totalDays = 2;
		int totalPeriods = 4;

		TeacherInfo[] teacherInfos = new TeacherInfo[2];
		teacherInfos[0] = getTeacherInfo1();
		teacherInfos[1] = getTeacherInfo2();

		TeacherView[] teacherViews = TimeTableGenHelper.getTeacherViews(teacherInfos, totalDays, totalPeriods);
		assertEquals("size of returned array is not proper", teacherViews.length, 2);

		for (int i = 0; i < 2; i++) {
			assertEquals("teacher id is different", teacherInfos[i].getTeacherId(), teacherViews[i].getTeacherId());
		}
	}

	@Test
	public void testGetClassIdVsSubjectTeachersMap() {
		TeacherInfo[] teacherInfos = new TeacherInfo[]{getTeacherInfo1(), getTeacherInfo2()};
		ClassGroup[] classGroups = new ClassGroup[]{getClassGroup1(), getClassGroup2()};

		SubjectVsTeacher[][] expectedSubjTeachers = new SubjectVsTeacher[2][2];
		expectedSubjTeachers[0][0] = new SubjectVsTeacher("MATHS", 1);
		expectedSubjTeachers[0][1] = new SubjectVsTeacher("PHY", 2);
		expectedSubjTeachers[1][0] = new SubjectVsTeacher("HINDI", 1);
		expectedSubjTeachers[1][1] = new SubjectVsTeacher("PHY", 2);

		Map<String, SubjectVsTeacher[]> classIdVsSubjectTeachers = TimeTableGenHelper.getMapClassIdVsSubjectTeachers(
				teacherInfos, classGroups);

		assertEquals("classId vs SubjectTeachers map is not proper", classIdVsSubjectTeachers.size(), 2);

		for (int i = 0; i < classGroups.length; i++) {
			String classId = classGroups[i].getClassGroupId();
			SubjectVsTeacher[] subjectVsTeachers = classIdVsSubjectTeachers.get(classId);
			assertEquals("subject teacher size is not proper", subjectVsTeachers.length, 2);

			for (int j = 0; j < 2; j++) {
				SubjectVsTeacher subjectVsTeacher = subjectVsTeachers[j];
				assertEquals("subject teacher - teacher id is incorrect",
						subjectVsTeacher.getTeacherId(), expectedSubjTeachers[i][j].getTeacherId());
				assertEquals("subject teacher - subject id is incorrect",
						subjectVsTeacher.getSubjectId(), expectedSubjTeachers[i][j].getSubjectId());
			}

		}
	}

	private ClassGroup getClassGroup1() {
		ClassGroup classGroup = new ClassGroup();
		classGroup.setClassGroupId("1A");
		classGroup.setSubjects(new String[]{"MATHS", "PHY"});
		classGroup.setTotalStudents(40);
		return classGroup;
	}

	private ClassGroup getClassGroup2() {
		ClassGroup classGroup = new ClassGroup();
		classGroup.setClassGroupId("2B");
		classGroup.setSubjects(new String[]{"HINDI", "PHY"});
		classGroup.setTotalStudents(35);
		return classGroup;
	}

	private TeacherInfo getTeacherInfo1() {
		TeacherInfo teacherInfo = new TeacherInfo();
		teacherInfo.setTeacherId(1);
		teacherInfo.setWeeklyTeachingHours(4);

		SubjectClassGroup group1 = new SubjectClassGroup();
		group1.setClassGroup("1A");
		group1.setSubject("MATHS");
		SubjectClassGroup group2 = new SubjectClassGroup();
		group2.setClassGroup("2B");
		group2.setSubject("HINDI");
		SubjectClassGroup[] subjectClassGroups = new SubjectClassGroup[]{group1, group2};

		teacherInfo.setSubjectClassGroups(subjectClassGroups);

		return teacherInfo;
	}

	private TeacherInfo getTeacherInfo2() {
		TeacherInfo teacherInfo = new TeacherInfo();
		teacherInfo.setTeacherId(2);
		teacherInfo.setWeeklyTeachingHours(4);

		SubjectClassGroup group1 = new SubjectClassGroup();
		group1.setClassGroup("1A");
		group1.setSubject("PHY");
		SubjectClassGroup group2 = new SubjectClassGroup();
		group2.setClassGroup("2B");
		group2.setSubject("PHY");
		SubjectClassGroup[] subjectClassGroups = new SubjectClassGroup[]{group1, group2};

		teacherInfo.setSubjectClassGroups(subjectClassGroups);

		return teacherInfo;
	}
}
