package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.SubjectVsTeacher;
import com.timetable.server.engine.model.common.TeacherView;
import com.timetable.server.engine.model.input.TeacherInfo;
import java.util.HashMap;
import java.util.Map;

public class DomainStore {
	private ClassView[] classViews;
	private TeacherView[] teacherViews;
	private Map<Integer, Integer> idVsConsumedPeriods = new HashMap<>();
	private Map<Integer, ClassView> idVsClassViews = new HashMap<>();
	private Map<Integer, TeacherView> idVsTeacherViews = new HashMap<>();

	public DomainStore(TeacherInfo[] teacherInfos, ClassView[] classViews, TeacherView[] teacherViews) {
		this.classViews = classViews;
		this.teacherViews = teacherViews;
		initializeTeacherInfos(teacherInfos);
		initializeClassViews(classViews);
		initializeTeacherViews(teacherViews);
	}

	private void initializeTeacherInfos(TeacherInfo[] teacherInfos) {
		for (TeacherInfo teacherInfo : teacherInfos) {
			idVsConsumedPeriods.put(teacherInfo.getTeacherId(), teacherInfo.getWeeklyTeachingHours());
		}
	}

	private void initializeClassViews(ClassView[] classViews) {
		for (ClassView classView : classViews) {
			idVsClassViews.put(classView.getClassGroupId(), classView);
		}
	}

	private void initializeTeacherViews(TeacherView[] teacherViews) {
		for (TeacherView teacherView : teacherViews) {
			idVsTeacherViews.put(teacherView.getTeacherId(), teacherView);
		}
	}

	public boolean canConsumePeriods(int teacherId, int periods) {
		Integer consumedPeriods = idVsConsumedPeriods.get(teacherId);
		consumedPeriods = consumedPeriods - periods;
		return consumedPeriods >= 0 ? true : false;
	}

	public void consumePeriods(int teacherId, int periods) {
		Integer consumedPeriods = idVsConsumedPeriods.get(teacherId);
		consumedPeriods = consumedPeriods - periods;
		idVsConsumedPeriods.put(teacherId, consumedPeriods);
	}

	public void undoConsumePeriods(int teacherId, int periods) {
		consumePeriods(teacherId, -periods);
	}

	public void updateClassView(int classX, SubjectVsTeacher subjectVsTeacher, int day, int period) {
		ClassView classView = idVsClassViews.get(classX);
		classView.setSubjectVsTeacher(day, period, subjectVsTeacher);
	}

	// MAYBE this is not needed.
	public void undoUpdateClassView(int classX, SubjectVsTeacher subjectVsTeacher, int day, int period) {
		ClassView classView = idVsClassViews.get(classX);
		classView.setSubjectVsTeacher(day, period, subjectVsTeacher);
	}

	public ClassView[] getClassViews() {
		return classViews;
	}

	public TeacherView[] getTeacherViews() {
		return teacherViews;
	}
}
