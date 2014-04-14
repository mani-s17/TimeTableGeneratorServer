package com.timetable.server.engine.generator.constraints;

import com.timetable.server.engine.generator.DomainStore;
import java.util.List;

public class CheckConstraints {

	private List<ConstraintChecker> constraintCheckers;

	public CheckConstraints(List<ConstraintChecker> constraintCheckers) {
		this.constraintCheckers = constraintCheckers;
	}

	public boolean checkConstraintsAreValid() {
		for (ConstraintChecker constraintChecker : constraintCheckers) {
			if (!constraintChecker.checkConstraint())
				return false;
		}

		return true;
	}

}
