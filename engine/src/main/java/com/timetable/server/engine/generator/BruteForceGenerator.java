package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.common.ClassView;
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

	private boolean stop = false;

	public BruteForceGenerator(int totalDays, int totalClasses, int totalPeriods, int totalTeachers, String[] classIds,
							   DomainStore domainStore, Map<String, SubjectVsTeacher[]> classIdVsSubjectTeachers) {
		this.totalDays = totalDays;
		this.totalClasses = totalClasses;
		this.totalPeriods = totalPeriods;
		this.totalTeachers = totalTeachers;
		this.classIds = classIds;
		this.domainStore = domainStore;
		this.classIdVsSubjectTeachers = classIdVsSubjectTeachers;
	}

	// TODO take care of entities starting from 1 and not 0.
	@Override
	public SampleOutput generateTimeTable(TimeTableInput timeTableInput) {
		int startClassIdx = 0;
		int startDay = 0;
		int startPeriod = 0;
		stop = false;
		assignRecursive(startClassIdx, startDay, startPeriod);
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
			if (canUse(subjectVsTeacher)) {
				continue;
			}
			// try using this teacher
			useAndUpdateState(subjectVsTeacher, classId, day, period);

			if (doneWithTheClass(day, period)) {
				// start afresh for the next class.
				assignRecursive(classIdx + 1, 1, 1);
			}
			else {
				// else continue with next period/day, whatever it is.
				int[] nextDayPeriod = getNextDayPeriod(day, period);
				int nextDay = nextDayPeriod[0];
				int nextPeriod = nextDayPeriod[1];
				assignRecursive(classIdx, nextDay, nextPeriod);
			}

			// check if we can stop after doing this, so that we don't lose the last update.
			if (stop)
				return;

			// undo using this teacher, so that we can try next combination
			undoUseAndUpdateState(subjectVsTeacher, classId, day, period);
		}
	}

	private boolean canUse(SubjectVsTeacher subjectVsTeacher) {
		return domainStore.canConsumePeriods(subjectVsTeacher.getTeacherId(), 1);
	}

	private void useAndUpdateState(SubjectVsTeacher subjectVsTeacher, String classX, int day, int period) {
		domainStore.consumePeriods(subjectVsTeacher.getTeacherId(), 1);
		domainStore.updateClassView(classX, subjectVsTeacher, day, period);
	}

	private void undoUseAndUpdateState(SubjectVsTeacher subjectVsTeacher, String classX, int day, int period) {
		domainStore.undoConsumePeriods(subjectVsTeacher.getTeacherId(), 1);
		domainStore.undoUpdateClassView(classX, day, period);
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

	}

	private SampleOutput getSampleOutput() {
		SampleOutput sampleOutput = new SampleOutput(totalClasses, totalTeachers);
		for (ClassView classView : domainStore.getClassViews()) {
			sampleOutput.setClassView(classView.getClassGroupId(), classView);
		}

		for (TeacherView teacherView : domainStore.getTeacherViews()) {
			sampleOutput.setTeacherView(teacherView.getTeacherId(), teacherView);
		}

		return sampleOutput;
	}

	private boolean checkAllConstraintsAreValid() {
		return true;
	}
}
