package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.TeacherView;
import com.timetable.server.engine.model.input.ClassGroup;
import com.timetable.server.engine.model.input.TeacherInfo;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 4/5/2014
 * Time: 11:27 PM
 */
public class DomainStoreTest {

	private final int totalDays = 2;
	private final int totalPeriods = 4;

	private DomainStore domainStore;

	@Before
	public void setUp() {
		TeacherInfo[] teacherInfos = TimeTableGenHelperTest.getTeacherInfos();
		ClassGroup[] classGroups = TimeTableGenHelperTest.getClassGroups();

		ClassView[] classViews = TimeTableGenHelper.getClassViews(classGroups, totalDays, totalPeriods);
		TeacherView[] teacherViews = TimeTableGenHelper.getTeacherViews(teacherInfos, totalDays, totalPeriods);

		domainStore = new DomainStore(teacherInfos, classViews, teacherViews);
	}

	@Test
	public void testNothingNow() {

	}
}
