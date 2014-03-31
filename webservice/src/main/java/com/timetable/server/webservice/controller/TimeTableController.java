package com.timetable.server.webservice.controller;

import com.timetable.server.engine.model.input.TimeTableInput;
import com.timetable.server.engine.model.output.TimeTableOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 3/29/2014
 * Time: 10:57 PM
 */
@Controller
@RequestMapping("/generate")
public class TimeTableController
{
	@RequestMapping(method = RequestMethod.POST, headers = "content-type=application/json")
	public @ResponseBody TimeTableOutput generateTimeTable(@RequestBody TimeTableInput name)
	{
		TimeTableOutput timeTable = new TimeTableOutput();
		timeTable.setTeacherId("mani");
		timeTable.setClasses(new String[] {"7a", "7b"});
		return timeTable;
	}
}
