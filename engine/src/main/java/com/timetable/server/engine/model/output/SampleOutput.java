package com.timetable.server.engine.model.output;

import java.util.HashMap;
import java.util.Map;

import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.TeacherView;

public class SampleOutput {
	private int totalClassGroups;
	private int totalTeachers;

	private Map<String, ClassView> classIdVsClassView;
	private Map<Integer, TeacherView> teacherIdVsTeacherView;

	public SampleOutput(int totalClassGroups, int totalTeachers) {
		this.totalClassGroups = totalClassGroups;
		this.totalTeachers = totalTeachers;

		classIdVsClassView = new HashMap<>();
		teacherIdVsTeacherView = new HashMap<>();
	}

	public void setClassView(String classGroupId, ClassView classView) {
		classIdVsClassView.put(classGroupId, classView);
	}

	public ClassView getClassView(String classGroupId) {
		return classIdVsClassView.get(classGroupId);
	}

	public void setTeacherView(int teacherId, TeacherView teacherView) {
		teacherIdVsTeacherView.put(teacherId, teacherView);
	}

	public TeacherView getTeacherView(int teacherId) {
		return teacherIdVsTeacherView.get(teacherId);
	}
}
