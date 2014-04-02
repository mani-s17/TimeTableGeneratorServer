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
		return null;
	}
}
