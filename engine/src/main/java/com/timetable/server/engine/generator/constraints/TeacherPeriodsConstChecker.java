package com.timetable.server.engine.generator.constraints;

import com.timetable.server.engine.generator.DomainStore;
import com.timetable.server.engine.model.common.TeacherView;

public class TeacherPeriodsConstChecker implements ConstraintChecker {

	public DomainStore domainStore;

	public TeacherPeriodsConstChecker(DomainStore domainStore) {
		this.domainStore = domainStore;
	}

	@Override
	public boolean checkConstraint() {
		for (TeacherView teacherView: domainStore.getTeacherViews()) {
			int remainingPeriods = domainStore.getRemainingPeriods(teacherView.getTeacherId());
			if (remainingPeriods > 0)
				return false;
		}

		return true;
	}
}
