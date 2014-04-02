package com.timetable.server.engine.generator;

import com.timetable.server.engine.model.input.TimeTableInput;
import com.timetable.server.engine.model.output.SampleOutput;

public interface TimeTableGenerator {

	public SampleOutput generateTimeTable(TimeTableInput timeTableInput);
}
