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

	public ClassView[] getClassViews() {
		return classViews;
	}

	public void setClassViews(ClassView[] classViews) {
		this.classViews = classViews;
	}

	public TeacherView[] getTeacherViews() {
		return teacherViews;
	}

	public void setTeacherViews(TeacherView[] teacherViews) {
		this.teacherViews = teacherViews;
	}
}
