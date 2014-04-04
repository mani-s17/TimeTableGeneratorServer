package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.common.SubjectVsTeacher;
import com.timetable.server.engine.model.input.TimeTableInput;
import com.timetable.server.engine.model.output.SampleOutput;

/**
 * Brute force implementation of the generator. we just keep trying all the possible values
 * and stop at the first solution obtained.
 *
 */
public class BruteForceGenerator implements TimeTableGenerator {

	private int totalDays;
	private int totalClasses;
	private int totalPeriods;

	private DomainStore domainStore;
	private SubjectVsTeacher[] subjectVsTeachers;

	private SampleOutput sampleOutput;

	private boolean stop = false;

	// TODO take care of entities starting from 1 and not 0.
	@Override
	public SampleOutput generateTimeTable(TimeTableInput timeTableInput) {



		return null;
	}


	/*
	  Outline of the steps this algorithm should do.
	  Basically a brute force algorithm, which involves deep recursion.

	  assignRecursive(classX, dayX, periodX, STATE) {
	  if (allClassesDone(classX)
	  	STOP;

		for (subject_teacher : subj_teachers) {
			if (!checkIfValidIfUsed(STATE, ....)) // check all the constraints in here...MAYBE use the DomainStore here.
				return;
			updateState(STATE, ...)

			if (doneWithThisClass(STATE)) {
				assignRecursive(nextClass, dayInit, periodInit, STATE);
			}
			else {
				(dayNext, periodNext) --> get the next day and period.
				assignRecursive(classX, dayNext, periodNext, STATE);
			}

			undoUpdate(STATE, ...) // undo update so that we can try a different combination.
		}
	  }
	 */
	private void assignRecursive(int classX, int day, int period) {
		if (classX > totalClasses) {
			return;
		}

		for (SubjectVsTeacher subjectVsTeacher : subjectVsTeachers) {
			if (!checkIfValidIsUsed(subjectVsTeacher)) {
				continue;
			}
			// try using this teacher
			useAndUpdateState(subjectVsTeacher, classX, day, period);

			if (doneWithTheClass(classX)) {
				// start afresh for the next class.
				assignRecursive(classX + 1, 1, 1);
			}
			else {
				int[] nextDayPeriod = getNextDayPeriod(day, period);
				int nextDay = nextDayPeriod[0];
				int nextPeriod = nextDayPeriod[1];
				assignRecursive(classX, nextDay, nextPeriod);
			}

			// undo using this teacher, so that we can try next combination
			undoUseAndUpdateState(subjectVsTeacher, classX, day, period);
		}
	}

	private boolean checkIfValidIsUsed(SubjectVsTeacher subjectVsTeacher) {
		return domainStore.canConsumePeriods(subjectVsTeacher.getTeacherId(), 1);
	}

	private void useAndUpdateState(SubjectVsTeacher subjectVsTeacher, int classX, int day, int period) {
		domainStore.consumePeriods(subjectVsTeacher.getTeacherId(), 1);
	}

	private void undoUseAndUpdateState(SubjectVsTeacher subjectVsTeacher, int classX, int day, int period) {
		domainStore.undoConsumePeriods(subjectVsTeacher.getTeacherId(), 1);
	}

	private boolean doneWithTheClass(int classX) {
		return false;
	}

	// return the next_dat and next_period.
	private int[] getNextDayPeriod(int day, int period) {
		// increment period
		int nextPeriod = (period % totalPeriods) + 1;
		// check if overflown
		int nextDay;
		if (nextPeriod > period) {
			nextDay = day;
		} else { // if period overflows, goto next day
			nextDay = (day % totalDays) + 1;
		}

		return new int[]{nextDay, nextPeriod};
	}

	// TODO ... log this state... this is to test the status of the domain store when the algorithm stopped.
	private void logThisState() {

	}
}
