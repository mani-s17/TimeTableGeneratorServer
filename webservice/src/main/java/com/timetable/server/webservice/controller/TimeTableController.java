package com.timetable.server.webservice.controller;

import com.timetable.server.engine.model.input.RawInput;
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
	public @ResponseBody String generateTimeTable(@RequestBody RawInput input)
	{
		StringBuilder response = new StringBuilder();
		response.append("Working Days Per Week - ");
		response.append(input.getWorkingDays());
		response.append("\n Number of Periods Per day - ");
		response.append(input.getPeriodPerDay());
		return response.toString();
	}
}
