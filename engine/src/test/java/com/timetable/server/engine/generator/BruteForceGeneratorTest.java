package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.TeacherView;
import com.timetable.server.engine.model.input.ClassGroup;
import com.timetable.server.engine.model.input.TeacherInfo;
import com.timetable.server.engine.model.input.TimeTableInput;
import com.timetable.server.engine.model.output.SampleOutput;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 4/5/2014
 * Time: 11:28 PM
 */
public class BruteForceGeneratorTest {

	private TimeTableGeneratorFactory timeTableGeneratorFactory = new TimeTableGeneratorFactory();
	private TimeTableGenerator bruteForceGenerator;


	@Test
	public void testRun() {
		TimeTableInput timeTableInput = getTimeTableInput();
		bruteForceGenerator = timeTableGeneratorFactory.getBruteForceGenerator(timeTableInput);

		SampleOutput sampleOutput = bruteForceGenerator.generateTimeTable(timeTableInput);

		System.out.println("------------- SAMPLE OUTPUT TEACHER VIEW -------------------\n");
		TeacherView[] teacherViews = sampleOutput.getTeacherViews();
		for (int i = 0; i < teacherViews.length; i++) {
			TeacherView teacherView = teacherViews[i];
			System.out.println("----------- TEACHER VIEW " + teacherView.getTeacherId() + " ---------------");

			System.out.println(teacherView);
		}

		System.out.println("\n\n-------------- SAMPLE OUTPUT CLASSVIEW -----------------\n");
		ClassView[] classViews = sampleOutput.getClassViews();

		for (int i = 0; i < classViews.length; i++) {
			ClassView classView = classViews[i];
			System.out.println("------------ CLASS VIEW " + classView.getClassGroupId() + " ----------------");

			System.out.println(classView);
		}
	}

	private TimeTableInput getTimeTableInput() {
		TimeTableInput timeTableInput = new TimeTableInput();
		timeTableInput.setTotalDays(2);
		timeTableInput.setTotalPeriods(2);
		timeTableInput.setTotalTeachers(2);
		timeTableInput.setTotalClasses(2);
		TeacherInfo[] teacherInfos = TestHelper.getTeacherInfos();
		ClassGroup[] classGroups = TestHelper.getClassGroups();
		timeTableInput.setTeacherInfos(teacherInfos);
		timeTableInput.setClassGroups(classGroups);

		return timeTableInput;
	}
}
