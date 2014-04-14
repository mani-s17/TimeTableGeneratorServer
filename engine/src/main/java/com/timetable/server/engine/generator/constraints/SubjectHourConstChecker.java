package com.timetable.server.engine.generator.constraints;

import com.timetable.server.engine.generator.DomainStore;
import com.timetable.server.engine.model.common.ClassView;

public class SubjectHourConstChecker implements ConstraintChecker {

	private DomainStore domainStore;

	public SubjectHourConstChecker(DomainStore domainStore) {
		this.domainStore = domainStore;
	}

	@Override
	public boolean checkConstraint() {
		for (ClassView classView : domainStore.getClassViews()) {
			String[] subjects = classView.getClassGroup().getSubjects();

			for (String subject : subjects) {
				Integer diff = classView.getActualTeachingHours(subject) - classView.getExpectedTeachingHours(subject);
				if (diff != 0)
					return false;
			}
		}

		return true;
	}
}
