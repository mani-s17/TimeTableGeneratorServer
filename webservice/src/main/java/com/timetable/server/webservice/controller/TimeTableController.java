package com.timetable.server.webservice.controller;

import java.io.IOException;

import com.timetable.server.engine.model.input.RawInput;
import com.timetable.server.engine.model.output.RawOutput;
import org.codehaus.jackson.map.ObjectMapper;
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
	public @ResponseBody RawOutput generateTimeTable(@RequestBody RawInput input)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		RawOutput rawOutput = null;
		try
		{
			rawOutput = objectMapper.readValue(getRawOutputJson(), RawOutput.class);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return rawOutput;
	}

	private String getRawOutputJson()
	{
		String rawOutputJson = "{" +
				"\"teachersView\":" +
				"[" +
				"{" +
				"        \"id\":1," +
				"        \"dayToPeriodToClassGroupSubjectMap\":" +
				"        {" +
				"        \"1\":{\"1\":[\"1\",\"Sub1\"],\"2\":[\"3\",\"Sub2\"]}," +
				"        \"2\":{\"1\":[\"1\",\"Sub1\"],\"2\":[\"3\",\"Sub2\"]}," +
				"        \"3\":{\"1\":[\"1\",\"Sub1\"],\"2\":[\"3\",\"Sub2\"]}," +
				"        \"4\":{\"1\":[\"1\",\"Sub1\"],\"2\":[\"3\",\"Sub2\"]}," +
				"        \"5\":{\"1\":[\"1\",\"Sub1\"],\"2\":[\"3\",\"Sub2\"]}" +
				"        }" +
				"        }," +
				"        {" +
				"\"id\":2," +
				"\"dayToPeriodToClassGroupSubjectMap\":" +
				"{" +
				"\"1\":{\"1\":[\"2\",\"Sub1\"],\"2\":[\"4\",\"Sub2\"]}," +
				"\"2\":{\"1\":[\"2\",\"Sub1\"],\"2\":[\"4\",\"Sub2\"]}," +
				"\"3\":{\"1\":[\"2\",\"Sub1\"],\"2\":[\"4\",\"Sub2\"]}," +
				"\"4\":{\"1\":[\"2\",\"Sub1\"],\"2\":[\"4\",\"Sub2\"]}," +
				"\"5\":{\"1\":[\"2\",\"Sub1\"],\"2\":[\"4\",\"Sub2\"]}" +
				"}" +
				"}" +
				"]," +
				"\"classGroupsView\":" +
				"[" +
				"{" +
				"\"id\":1," +
				"\"dayToSequencedPeriodsMap\":" +
				"{" +
				"\"1\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\",\"Sub6\",\"Sub7\",\"Sub8\"]," +
				"\"2\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\",\"Sub6\",\"Sub7\",\"Sub8\"]," +
				"\"3\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\",\"Sub6\",\"Sub7\",\"Sub8\"]," +
				"\"4\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\",\"Sub6\",\"Sub7\",\"Sub8\"]," +
				"\"5\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\",\"Sub6\",\"Sub7\",\"Sub8\"]" +
				"}" +
				"}," +
				"{" +
				"\"id\":2," +
				"\"dayToSequencedPeriodsMap\":" +
				"{" +
				"\"1\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\",\"Sub6\",\"Sub7\",\"Sub8\"]," +
				"\"2\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\",\"Sub6\",\"Sub7\",\"Sub8\"]," +
				"\"3\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\",\"Sub6\",\"Sub7\",\"Sub8\"]," +
				"\"4\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\",\"Sub6\",\"Sub7\",\"Sub8\"]," +
				"\"5\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\",\"Sub6\",\"Sub7\",\"Sub8\"]" +
				"}" +
				"}" +
				"]" +
				"}";
		return rawOutputJson;
	}
}
