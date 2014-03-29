package com.timetable.server.webservice.controller;

import com.timetable.server.webservice.model.output.TimeTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public @ResponseBody
	TimeTable generateTimeTable(@PathVariable String name)
	{
		TimeTable timeTable = new TimeTable();
		timeTable.setTeacherId(name);
		timeTable.setClasses(new String[] {"7a", "7b"});
		return timeTable;
	}
}
