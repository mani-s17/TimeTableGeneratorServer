package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.common.ClassGroupVsSubject;
import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.SubjectVsTeacher;
import com.timetable.server.engine.model.common.TeacherView;
import com.timetable.server.engine.model.input.ClassGroup;
import com.timetable.server.engine.model.input.TeacherInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
		TeacherInfo[] teacherInfos = TestHelper.getTeacherInfos();
		ClassGroup[] classGroups = TestHelper.getClassGroups();

		ClassView[] classViews = TimeTableGenHelper.getClassViews(classGroups, totalDays, totalPeriods);
		TeacherView[] teacherViews = TimeTableGenHelper.getTeacherViews(teacherInfos, totalDays, totalPeriods);

		domainStore = new DomainStore(teacherInfos, classViews, teacherViews);
	}

	@Test
	public void testConsumePeriods() {
		// initially the consuming periods is allowed initially.
		assertTrue("consumption of periods is improper", domainStore.canConsumePeriods(1, 1));
		assertTrue("consumption of periods is improper",domainStore.canConsumePeriods(2, 1));

		// after consumption, the state should be preserved.
		domainStore.consumePeriods(1, 4);
		domainStore.consumePeriods(2, 3);

		// once exhausted consumption of more periods should not be allowed
		assertFalse("consumption of periods is improper", domainStore.canConsumePeriods(1, 1));
		assertTrue("consumption of periods is improper", domainStore.canConsumePeriods(2, 1));

		// consumed periods is also proper
		assertEquals("consumption of periods is improper", domainStore.getConsumedPeriods(1), 0);
		assertEquals("consumption of periods is improper", domainStore.getConsumedPeriods(2), 1);

		// after undo the consumed periods should be reverted.
		domainStore.undoConsumePeriods(1, 2);

		assertTrue("consumption-undo of periods is improper", domainStore.canConsumePeriods(1, 2));
		assertEquals("consumption-undo of periods is improper", domainStore.getConsumedPeriods(1), 2);
	}

	@Test
	public void testUpdateClassView() {
		SubjectVsTeacher subjectVsTeacher1 = new SubjectVsTeacher("PHY", 2);
		domainStore.updateClassView("1A", subjectVsTeacher1, 0, 0);

		SubjectVsTeacher subjectVsTeacher2 = new SubjectVsTeacher("MATHS", 1);
		domainStore.updateClassView("1A", subjectVsTeacher2, 0, 1);

		ClassView classView = domainStore.getClassView("1A");
		SubjectVsTeacher actualSubjectTeacher1 = classView.getSubjectVsTeacher(0, 0);
		assertEquals("class view update is improper", actualSubjectTeacher1, subjectVsTeacher1);

		SubjectVsTeacher actualSubjectVsTeacher2 = classView.getSubjectVsTeacher(0, 1);
		assertEquals("class view update is improper", actualSubjectVsTeacher2, subjectVsTeacher2);

		// test the undo operation.
		domainStore.undoUpdateClassView("1A", 0, 0);
		assertNull("class view update undo is improper", classView.getSubjectVsTeacher(0, 0));
	}

	@Test
	public void testUpdateTeacherView() {
		int teacherId = 1;
		String classX1 = "1A";
		String subject1 = "PHY";
		domainStore.updateTeacherView(teacherId, classX1, subject1, 0, 0);

		String classX2 = "2B";
		String subject2 = "PHY";
		domainStore.updateTeacherView(teacherId, classX2, subject2, 0, 1);

		TeacherView teacherView = domainStore.getTeacherView(teacherId);

		ClassGroupVsSubject expClassGroupVsSubject1 = new ClassGroupVsSubject(classX1, subject1);
		assertEquals("teacher view update is improper", teacherView.getClassGroupVsSubject(0, 0), expClassGroupVsSubject1);
		ClassGroupVsSubject expClassGroupVsSubject2 = new ClassGroupVsSubject(classX2, subject2);
		assertEquals("teacher view update is improper", teacherView.getClassGroupVsSubject(0, 1), expClassGroupVsSubject2);

		// test the undo operation.
		domainStore.undoUpdateTeacherView(teacherId, 0, 1);
		assertNull("teacher view update undo is improper", teacherView.getClassGroupVsSubject(0, 1));
	}
}
