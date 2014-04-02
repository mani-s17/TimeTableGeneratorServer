package com.timetable.server.engine.model.output;

import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.TeacherView;

public class SampleOutput {
	private int totalClassGroups;
	private int totalTeachers;

	private ClassView[] classViews;
	private TeacherView[] teacherViews;

	public SampleOutput(int totalClassGroups, int totalTeachers) {
		this.totalClassGroups = totalClassGroups;
		this.totalTeachers = totalTeachers;

		classViews = new ClassView[totalClassGroups];
		teacherViews = new TeacherView[totalTeachers];
	}

	public void setClassView(int classGroupId, ClassView classView) {
		classViews[classGroupId] = classView;
	}

	public ClassView getClassView(int classGroupId) {
		return classViews[classGroupId];
	}

	public void setTeacherView(int teacherId, TeacherView teacherView) {
		teacherViews[teacherId] = teacherView;
	}

	public TeacherView getTeacherView(int teacherId) {
		return teacherViews[teacherId];
	}
}
