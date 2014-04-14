package com.timetable.server.engine.generator.constraints;

import com.timetable.server.engine.generator.DomainStore;
import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.SubjectVsTeacher;
import com.timetable.server.engine.model.common.TeacherView;

public class FutureConstraintsChecker {


	private DomainStore domainStore;

	public FutureConstraintsChecker(DomainStore domainStore) {
		this.domainStore = domainStore;
	}


	public boolean canUse(String classGroupId, SubjectVsTeacher subjectVsTeacher, int day, int period) {
		int teacherId = subjectVsTeacher.getTeacherId();
		// check max periods.
		if (!domainStore.canConsumePeriods(subjectVsTeacher.getTeacherId(), 1))
			return false;

		// check conflicts with other assignments of teacher.
		TeacherView teacherView = domainStore.getTeacherView(teacherId);
		if (teacherView.getClassGroupVsSubject(day, period) != null)
			return false; // since already engaged elsewhere.

		// conflicts with class-group.
		ClassView classView = domainStore.getClassView(classGroupId);
		if (classView.getSubjectVsTeacher(day, period) != null)
			return false; // class already has some other class.

		return true;
	}
}
