package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.common.ClassGroupVsSubject;
import java.util.HashMap;
import java.util.Map;

import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.SubjectVsTeacher;
import com.timetable.server.engine.model.common.TeacherView;
import com.timetable.server.engine.model.input.TeacherInfo;

/**
 * The class which represents the state of the timetable as the algorithm is running.
 */
public class DomainStore {
	private ClassView[] classViews;
	private TeacherView[] teacherViews;
	private Map<Integer, Integer> teacherIdVsConsumedPeriods = new HashMap<>();
	private Map<String, ClassView> classIdVsClassViews = new HashMap<>();
	private Map<Integer, TeacherView> teacherIdVsTeacherViews = new HashMap<>();

	public DomainStore(TeacherInfo[] teacherInfos, ClassView[] classViews, TeacherView[] teacherViews) {
		this.classViews = classViews;
		this.teacherViews = teacherViews;
		initializeTeacherInfos(teacherInfos);
		initializeClassViews(classViews);
		initializeTeacherViews(teacherViews);
	}

	private void initializeTeacherInfos(TeacherInfo[] teacherInfos) {
		for (TeacherInfo teacherInfo : teacherInfos) {
			teacherIdVsConsumedPeriods.put(teacherInfo.getTeacherId(), teacherInfo.getWeeklyTeachingHours());
		}
	}

	private void initializeClassViews(ClassView[] classViews) {
		for (ClassView classView : classViews) {
			classIdVsClassViews.put(classView.getClassGroupId(), classView);
		}
	}

	private void initializeTeacherViews(TeacherView[] teacherViews) {
		for (TeacherView teacherView : teacherViews) {
			teacherIdVsTeacherViews.put(teacherView.getTeacherId(), teacherView);
		}
	}

	public ClassView[] getClassViews() {
		return classViews;
	}

	public TeacherView[] getTeacherViews() {
		return teacherViews;
	}

	public boolean canConsumePeriods(int teacherId, int periods) {
		Integer consumedPeriods = teacherIdVsConsumedPeriods.get(teacherId);
		consumedPeriods = consumedPeriods - periods;
		return consumedPeriods >= 0;
	}

	public void consumePeriods(int teacherId, int periods) {
		Integer consumedPeriods = teacherIdVsConsumedPeriods.get(teacherId);
		consumedPeriods = consumedPeriods - periods;
		teacherIdVsConsumedPeriods.put(teacherId, consumedPeriods);
	}

	public void undoConsumePeriods(int teacherId, int periods) {
		consumePeriods(teacherId, -periods);
	}

	public int getConsumedPeriods(int teacherId) {
		return teacherIdVsConsumedPeriods.get(teacherId);
	}

	public void updateClassView(String classX, SubjectVsTeacher subjectVsTeacher, int day, int period) {
		ClassView classView = classIdVsClassViews.get(classX);
		classView.setSubjectVsTeacher(day, period, subjectVsTeacher);
	}

	// MAYBE this is not needed.
	public void undoUpdateClassView(String classX, int day, int period) {
		ClassView classView = classIdVsClassViews.get(classX);
		classView.setSubjectVsTeacher(day, period, null);
	}

	public ClassView getClassView(String classX) {
		return classIdVsClassViews.get(classX);
	}

	public void updateTeacherView(int teacherId, String classX, String subject, int day, int period) {
		TeacherView teacherView = teacherIdVsTeacherViews.get(teacherId);

		ClassGroupVsSubject classGroupVsSubject = new ClassGroupVsSubject(classX, subject);
		teacherView.setClassGroupVsSubject(day, period, classGroupVsSubject);
	}

	public void undoUpdateTeacherView(int teacherId, int day, int period) {
		TeacherView teacherView = teacherIdVsTeacherViews.get(teacherId);
		teacherView.setClassGroupVsSubject(day, period, null);
	}

	public TeacherView getTeacherView(int teacherId) {
		return teacherIdVsTeacherViews.get(teacherId);
	}
}
