package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.input.TimeTableInput;
import com.timetable.server.engine.model.output.SampleOutput;

/**
 * Brute force implementation of the generator. we just keep trying all the possible values
 * and stop at the first solution obtained.
 *
 */
public class BruteForceGenerator implements TimeTableGenerator {

	@Override
	public SampleOutput generateTimeTable(TimeTableInput timeTableInput) {

		/*
		  Outline of the steps this algorithm should do.
		  Basically a brute force algorithm, which involves deep recursion.

		  assignRecursive(classX, dayX, periodX, STATE) {
		  	for (subject_teacher : subj_teachers) {
		  		if (!checkIfValid(STATE, ....)) // check all the constraints in here...MAYBE use the DomainStore here.
		  			return;
		  		updateState(STATE, ...)

		  		if (doneWithThisClass(STATE)) {
		  			assignRecursive(nextClass, dayInit, periodInit, STATE);
		  		}
		  		else {
		  			(dayNext, periodNext) --> get the next day and period.
		  			assignRecursive(classX, dayNext, periodNext, STATE);
		  		}
		  	}
		  }
		 */
		return null;
	}
}
