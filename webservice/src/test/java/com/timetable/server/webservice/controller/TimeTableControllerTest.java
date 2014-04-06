package com.timetable.server.webservice.controller;

import com.timetable.server.engine.model.input.Input;
import com.timetable.server.engine.model.output.TimeTableOutput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 4/5/2014
 * Time: 11:12 PM
 */
public class TimeTableControllerTest
{
	private TimeTableController timeTableController;

	@Before
	public void setUp() throws Exception
	{
		timeTableController = new TimeTableController();
	}

	@Test
	public void testGenerateTimeTable() throws Exception
	{
		TimeTableOutput timeTableOutput = timeTableController.generateTimeTable(new Input());
		Assert.assertEquals("Mani", timeTableOutput.getTeacherId());
	}
}
