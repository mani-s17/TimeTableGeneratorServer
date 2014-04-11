package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.SubjectVsTeacher;
import com.timetable.server.engine.model.common.TeacherView;
import com.timetable.server.engine.model.input.ClassGroup;
import com.timetable.server.engine.model.input.TeacherInfo;
import java.sql.Time;
import java.util.Map;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TimeTableGenHelperTest {

	@Test
	public void testGetClassViews() {
		int totalDays = 2;
		int totalPeriods = 4;

		ClassGroup[] classGroups = new ClassGroup[2];
		classGroups[0] = TestHelper.getClassGroup1();
		classGroups[1] = TestHelper.getClassGroup2();

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
		classGroups[0] = TestHelper.getClassGroup1();
		classGroups[1] = TestHelper.getClassGroup2();

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
		teacherInfos[0] = TestHelper.getTeacherInfo1();
		teacherInfos[1] = TestHelper.getTeacherInfo2();

		TeacherView[] teacherViews = TimeTableGenHelper.getTeacherViews(teacherInfos, totalDays, totalPeriods);
		assertEquals("size of returned array is not proper", teacherViews.length, 2);

		for (int i = 0; i < 2; i++) {
			assertEquals("teacher id is different", teacherInfos[i].getTeacherId(), teacherViews[i].getTeacherId());
		}
	}

	@Test
	public void testGetClassIdVsSubjectTeachersMap() {
		TeacherInfo[] teacherInfos = new TeacherInfo[]{TestHelper.getTeacherInfo1(), TestHelper.getTeacherInfo2()};
		ClassGroup[] classGroups = new ClassGroup[]{TestHelper.getClassGroup1(), TestHelper.getClassGroup2()};

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

	@Test
	public void testNextDayPeriod() {
		int totalDays = 4; // days will vary from 0 to 3.
		int totalPeriods = 5; // periods will vary from 0 to 4.

		// simple period increment.
		int[] nextDayPeriod;
		nextDayPeriod = TimeTableGenHelper.getNextDayPeriod(1, 1, totalDays, totalPeriods);
		assertArrayEquals("next day - period calculation is not proper", nextDayPeriod, new int[]{1, 2});

		nextDayPeriod = TimeTableGenHelper.getNextDayPeriod(0, 0, totalDays, totalPeriods);
		assertArrayEquals("next day - period calculation is not proper", nextDayPeriod, new int[]{0, 1});

		// period overflow
		nextDayPeriod = TimeTableGenHelper.getNextDayPeriod(1, 4, totalDays, totalPeriods);
		assertArrayEquals("next day - period calculation is not proper", nextDayPeriod, new int[]{2, 0});

		//both overflow
		nextDayPeriod = TimeTableGenHelper.getNextDayPeriod(3, 4, totalDays, totalPeriods);
		assertArrayEquals("next day - period calculation is not proper", nextDayPeriod, new int[]{0, 0});
	}


}
