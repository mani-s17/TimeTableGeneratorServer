package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.input.Input;
import com.timetable.server.engine.model.output.Output;

/**
 * <br />Created with IntelliJ IDEA.
 * <br />User: Subramaniam S
 * <br />Date: 4/24/2014
 * <br />Time: 11:34 PM
 */
public interface TimeTableGenerator
{
	public Output generateTimeTable(Input input);
}
