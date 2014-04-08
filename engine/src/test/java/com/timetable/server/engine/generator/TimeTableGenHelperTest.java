package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.input.ClassGroup;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

	private ClassGroup getClassGroup1() {
		ClassGroup classGroup1 = new ClassGroup();
		classGroup1.setClassGroupId("1A");
		classGroup1.setSubjects(new String[]{"MATHS", "PHY"});
		classGroup1.setTotalStudents(40);
		return classGroup1;
	}

	private ClassGroup getClassGroup2() {
		ClassGroup classGroup2 = new ClassGroup();
		classGroup2.setClassGroupId("2B");
		classGroup2.setSubjects(new String[]{"HINDI", "PHY"});
		classGroup2.setTotalStudents(35);
		return classGroup2;
	}
}
