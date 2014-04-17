package com.timetable.server.webservice.controller;

import java.io.IOException;

import com.timetable.server.engine.generator.TimeTableGenerator;
import com.timetable.server.engine.generator.TimeTableGeneratorFactory;
import com.timetable.server.engine.helper.InputConverter;
import com.timetable.server.engine.helper.OutputConverter;
import com.timetable.server.engine.model.input.RawInput;
import com.timetable.server.engine.model.input.TimeTableInput;
import com.timetable.server.engine.model.output.RawOutput;
import com.timetable.server.engine.model.output.SampleOutput;
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

	private RawOutput generate(RawInput input)
	{
		TimeTableInput timeTableInput = InputConverter.convertRawInputToTimeTableInput(input);
		TimeTableGenerator generator = new TimeTableGeneratorFactory().getBruteForceGenerator(timeTableInput);
		SampleOutput output = generator.generateTimeTable(timeTableInput);
		return OutputConverter.convertSampleOutputToRawOutput(output, input.getWorkingDays(), input.getPeriodPerDay());
	}

	public static void main(String[] args)
	{
		//String input = "{\"workingDays\":5,\"periodPerDay\":8,\"teachers\":[{\"id\":1,\"hours\":18,\"standardToSubjectMap\":{\"1\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"],\"2\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"],\"3\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"]}},{\"id\":2,\"hours\":18,\"standardToSubjectMap\":{\"1\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"],\"2\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"],\"3\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"]}},{\"id\":3,\"hours\":15,\"standardToSubjectMap\":{\"1\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"],\"2\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"],\"3\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"]}},{\"id\":4,\"hours\":10,\"standardToSubjectMap\":{\"1\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"],\"2\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"],\"3\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"]}},{\"id\":5,\"hours\":11,\"standardToSubjectMap\":{\"1\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"],\"2\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"],\"3\":[\"Sub1\",\"Sub2\",\"Sub3\",\"Sub4\",\"Sub5\"]}}],\"classGroups\":[{\"id\":1,\"standardId\":1,\"subjectToHourMap\":{\"Sub1\":2,\"Sub2\":7,\"Sub3\":2,\"Sub4\":3,\"Sub5\":4}},{\"id\":2,\"standardId\":1,\"subjectToHourMap\":{\"Sub1\":1,\"Sub2\":5,\"Sub3\":7,\"Sub4\":1,\"Sub5\":9}},{\"id\":3,\"standardId\":1,\"subjectToHourMap\":{\"Sub1\":5,\"Sub2\":8,\"Sub3\":5,\"Sub4\":4,\"Sub5\":4}},{\"id\":4,\"standardId\":1,\"subjectToHourMap\":{\"Sub1\":2,\"Sub2\":3,\"Sub3\":3,\"Sub4\":2,\"Sub5\":1}},{\"id\":5,\"standardId\":2,\"subjectToHourMap\":{\"Sub1\":4,\"Sub2\":10,\"Sub3\":10,\"Sub4\":5,\"Sub5\":3}},{\"id\":6,\"standardId\":2,\"subjectToHourMap\":{\"Sub1\":1,\"Sub2\":2,\"Sub3\":10,\"Sub4\":2,\"Sub5\":5}},{\"id\":7,\"standardId\":2,\"subjectToHourMap\":{\"Sub1\":2,\"Sub2\":10,\"Sub3\":10,\"Sub4\":7,\"Sub5\":10}},{\"id\":8,\"standardId\":2,\"subjectToHourMap\":{\"Sub1\":3,\"Sub2\":6,\"Sub3\":9,\"Sub4\":7,\"Sub5\":7}},{\"id\":9,\"standardId\":3,\"subjectToHourMap\":{\"Sub1\":8,\"Sub2\":9,\"Sub3\":10,\"Sub4\":7,\"Sub5\":2}},{\"id\":10,\"standardId\":3,\"subjectToHourMap\":{\"Sub1\":2,\"Sub2\":1,\"Sub3\":3,\"Sub4\":8,\"Sub5\":6}},{\"id\":11,\"standardId\":3,\"subjectToHourMap\":{\"Sub1\":3,\"Sub2\":10,\"Sub3\":8,\"Sub4\":9,\"Sub5\":7}},{\"id\":12,\"standardId\":3,\"subjectToHourMap\":{\"Sub1\":5,\"Sub2\":9,\"Sub3\":7,\"Sub4\":10,\"Sub5\":3}}]}";
		/*String input = "{" +
				"\"workingDays\":5," +
				"\"periodPerDay\":8," +
				"\"teachers\":" +
				"[" +
				"{\"id\":1,\"hours\":24,\"standardToSubjectMap\":{\"11\":[\"Eng\"],\"12\":[\"Eng\"]}}," +
				"{\"id\":2,\"hours\":24,\"standardToSubjectMap\":{\"11\":[\"Hin\"],\"12\":[\"Hin\"]}}," +
				"{\"id\":3,\"hours\":24,\"standardToSubjectMap\":{\"11\":[\"Mat\"],\"12\":[\"Mat\"]}}," +
				"{\"id\":4,\"hours\":24,\"standardToSubjectMap\":{\"11\":[\"Phy\"],\"12\":[\"Phy\"]}}," +
				"{\"id\":5,\"hours\":24,\"standardToSubjectMap\":{\"11\":[\"Che\"],\"12\":[\"Che\"]}}," +
				"{\"id\":6,\"hours\":24,\"standardToSubjectMap\":{\"11\":[\"Comp\"],\"12\":[\"Comp\"]}}," +
				"{\"id\":7,\"hours\":24,\"standardToSubjectMap\":{\"11\":[\"Bio\"],\"12\":[\"Bio\"]}}," +
				"{\"id\":8,\"hours\":24,\"standardToSubjectMap\":{\"11\":[\"Free\"],\"12\":[\"Free\"]}}" +
				"]," +
				"\"classGroups\":" +
				"[" +
				"{\"id\":1,\"standardId\":11,\"subjectToHourMap\":{\"Eng\":4,\"Hin\":4,\"Mat\":7,\"Phy\":7,\"Che\":7,\"Comp\":7,\"Free\":4}}," +
				"{\"id\":2,\"standardId\":11,\"subjectToHourMap\":{\"Eng\":4,\"Hin\":4,\"Mat\":7,\"Phy\":7,\"Che\":7,\"Bio\":7,\"Free\":4}}," +
				"{\"id\":3,\"standardId\":12,\"subjectToHourMap\":{\"Eng\":4,\"Hin\":4,\"Mat\":7,\"Phy\":7,\"Che\":7,\"Comp\":7,\"Free\":4}}," +
				"{\"id\":4,\"standardId\":12,\"subjectToHourMap\":{\"Eng\":4,\"Hin\":4,\"Mat\":7,\"Phy\":7,\"Che\":7,\"Bio\":7,\"Free\":4}}" +
				"]" +
				"}";*/
		
		String input = "{" +
				"\"workingDays\":5," +
				"\"periodPerDay\":8," +
				"\"teachers\":" +
				"[" +
				"{\"id\":1,\"hours\":16,\"standardToSubjectMap\":{\"11\":[\"Eng\"],\"12\":[\"Eng\"]}}," +
				"{\"id\":2,\"hours\":16,\"standardToSubjectMap\":{\"11\":[\"Hin\"],\"12\":[\"Hin\"]}}," +
				"{\"id\":3,\"hours\":28,\"standardToSubjectMap\":{\"11\":[\"Mat\"],\"12\":[\"Mat\"]}}," +
				"{\"id\":4,\"hours\":28,\"standardToSubjectMap\":{\"11\":[\"Phy\"],\"12\":[\"Phy\"]}}," +
				"{\"id\":5,\"hours\":28,\"standardToSubjectMap\":{\"11\":[\"Che\"],\"12\":[\"Che\"]}}," +
				"{\"id\":6,\"hours\":14,\"standardToSubjectMap\":{\"11\":[\"Comp\"],\"12\":[\"Comp\"]}}," +
				"{\"id\":7,\"hours\":14,\"standardToSubjectMap\":{\"11\":[\"Bio\"],\"12\":[\"Bio\"]}}," +
				"{\"id\":8,\"hours\":28,\"standardToSubjectMap\":{\"11\":[\"Free\"],\"12\":[\"Free\"]}}" +
				"]," +
				"\"classGroups\":" +
				"[" +
				"{\"id\":1,\"standardId\":11,\"subjectToHourMap\":{\"Eng\":4,\"Hin\":4,\"Mat\":7,\"Phy\":7,\"Che\":7,\"Comp\":7,\"Free\":4}}," +
				"{\"id\":2,\"standardId\":11,\"subjectToHourMap\":{\"Eng\":4,\"Hin\":4,\"Mat\":7,\"Phy\":7,\"Che\":7,\"Bio\":7,\"Free\":4}}," +
				"{\"id\":3,\"standardId\":12,\"subjectToHourMap\":{\"Eng\":4,\"Hin\":4,\"Mat\":7,\"Phy\":7,\"Che\":7,\"Comp\":7,\"Free\":4}}," +
				"{\"id\":4,\"standardId\":12,\"subjectToHourMap\":{\"Eng\":4,\"Hin\":4,\"Mat\":7,\"Phy\":7,\"Che\":7,\"Bio\":7,\"Free\":4}}" +
				"]" +
				"}";
		ObjectMapper objectMapper = new ObjectMapper();
		try
		{
			RawInput rawInput = objectMapper.readValue(input, RawInput.class);
			TimeTableController obj = new TimeTableController();
			RawOutput rawOutput = obj.generate(rawInput);
			System.out.println("Done");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
