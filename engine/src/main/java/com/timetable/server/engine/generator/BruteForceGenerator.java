package com.timetable.server.engine.generator;

import com.sun.org.apache.regexp.internal.recompile;
import com.timetable.server.engine.generator.constraints.CheckConstraints;
import com.timetable.server.engine.generator.constraints.FutureConstraintsChecker;
import com.timetable.server.engine.model.common.SubjectVsTeacher;
import com.timetable.server.engine.model.common.TeacherView;
import com.timetable.server.engine.model.input.TimeTableInput;
import com.timetable.server.engine.model.output.SampleOutput;
import java.util.Map;

/**
 * Brute force implementation of the generator. we just keep trying all the possible values
 * and stop at the first solution obtained.
 *
 */
public class BruteForceGenerator implements TimeTableGenerator {

	private int totalDays;
	private int totalClasses;
	private int totalPeriods;
	private int totalTeachers;

	private String[] classIds;

	private DomainStore domainStore;

	private Map<String, SubjectVsTeacher[]> classIdVsSubjectTeachers;
	private CheckConstraints checkConstraints;

	private FutureConstraintsChecker futureConstraintsChecker;

	private boolean stop = false;

	public BruteForceGenerator(int totalDays, int totalClasses, int totalPeriods, int totalTeachers, String[] classIds,
							   DomainStore domainStore, Map<String, SubjectVsTeacher[]> classIdVsSubjectTeachers,
							   CheckConstraints checkConstraints) {
		this.totalDays = totalDays;
		this.totalClasses = totalClasses;
		this.totalPeriods = totalPeriods;
		this.totalTeachers = totalTeachers;
		this.classIds = classIds;
		this.domainStore = domainStore;
		this.classIdVsSubjectTeachers = classIdVsSubjectTeachers;
		this.checkConstraints = checkConstraints;
		this.futureConstraintsChecker = new FutureConstraintsChecker(domainStore);
	}

	// TODO take care of entities starting from 1 and not 0.
	@Override
	public SampleOutput generateTimeTable(TimeTableInput timeTableInput) {
		int startClassIdx = 0;
		int startDay = 0;
		int startPeriod = 0;
		stop = false;
		assignRecursive(startClassIdx, startDay, startPeriod);

		if (!stop)
			System.out.println("NO SOLUTION FOUND :( :( ");

		return getSampleOutput();
	}


	private void assignRecursive(int classIdx, int day, int period) {
		if (stop)
			return;

		if (classIdx >= totalClasses) {
			if (checkAllConstraintsAreValid()) {
				logThisState();
				stop = true;
			}
			return;
		}

		String classId = classIds[classIdx];
		SubjectVsTeacher[] subjectVsTeachers = classIdVsSubjectTeachers.get(classId);

		for (SubjectVsTeacher subjectVsTeacher : subjectVsTeachers) {
			if (!canUse(classId, subjectVsTeacher, day, period)) {
				continue;
			}
			// try using this teacher
			useAndUpdateState(subjectVsTeacher, classId, day, period);

			if (doneWithTheClass(day, period)) {
				// start afresh for the next class.
				assignRecursive(classIdx + 1, 0, 0);
			}
			else {
				// else continue with next period/day, whatever it is.
				int[] nextDayPeriod = getNextDayPeriod(day, period);
				int nextDay = nextDayPeriod[0];
				int nextPeriod = nextDayPeriod[1];
				assignRecursive(classIdx, nextDay, nextPeriod);
			}

			// check if we can stop after doing this, so that we don't lose the last update after getting a PROPER output.
			if (stop)
				return;

			// undo using this teacher, so that we can try next combination
			undoUseAndUpdateState(subjectVsTeacher, classId, day, period);
		}
	}

	private boolean canUse(String classGroupId, SubjectVsTeacher subjectVsTeacher, int day, int period) {
		return futureConstraintsChecker.canUse(classGroupId, subjectVsTeacher, day, period);
	}

	private void useAndUpdateState(SubjectVsTeacher subjectVsTeacher, String classX, int day, int period) {
		domainStore.consumePeriods(subjectVsTeacher.getTeacherId(), 1);
		domainStore.updateClassView(classX, subjectVsTeacher, day, period);
		domainStore.updateTeacherView(subjectVsTeacher.getTeacherId(), classX, subjectVsTeacher.getSubjectId(),
				day, period);
	}

	private void undoUseAndUpdateState(SubjectVsTeacher subjectVsTeacher, String classX, int day, int period) {
		domainStore.undoConsumePeriods(subjectVsTeacher.getTeacherId(), 1);
		domainStore.undoUpdateClassView(classX, day, period);
		domainStore.undoUpdateTeacherView(subjectVsTeacher.getTeacherId(), day, period);
	}

	private boolean doneWithTheClass(int day, int period) {
		// since we are traversing the days and periods in a linearly,
		// if we reach the last day/last period, we are done with this class.
		if (day == (totalDays - 1) && period == (totalPeriods - 1))
			return true;
		return false;
	}

	// return the next_dat and next_period.
	private int[] getNextDayPeriod(int day, int period) {
		return TimeTableGenHelper.getNextDayPeriod(day, period, totalDays, totalPeriods);
	}

	// TODO ... log this state... this is to print the status of the domain store when the algorithm stopped.
	private void logThisState() {
		System.out.println("GOT THE SOLUTION !!!");
	}

	private SampleOutput getSampleOutput() {
		SampleOutput sampleOutput = new SampleOutput(totalClasses, totalTeachers);
		sampleOutput.setClassViews(domainStore.getClassViews());
		sampleOutput.setTeacherViews(domainStore.getTeacherViews());

		return sampleOutput;
	}

	private boolean checkAllConstraintsAreValid() {
		return checkConstraints.checkConstraintsAreValid();
	}
}
