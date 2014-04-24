package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.input.Input;
import com.timetable.server.engine.model.output.Output;

/**
 * <br />Created with IntelliJ IDEA.
 * <br />User: Subramaniam S
 * <br />Date: 4/24/2014
 * <br />Time: 11:37 PM
 */
public abstract class AbstractTimeTableGenerator implements TimeTableGenerator
{
	public abstract Output allocatePeriods(Input input, Output output);

	@Override
	public Output generateTimeTable(Input input)
	{
		return allocatePeriods(input, initialiseOutput(input));
	}

	private Output initialiseOutput(Input input)
	{
		Output output = new Output();
		//TODO: Initialise Output with ClassGroupView and TeacherView based on Input
		return output;
	}
}
