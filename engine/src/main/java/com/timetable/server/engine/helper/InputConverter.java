package com.timetable.server.engine.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.timetable.server.engine.model.input.ClassGroup;
import com.timetable.server.engine.model.input.RawClassGroup;
import com.timetable.server.engine.model.input.RawInput;
import com.timetable.server.engine.model.input.RawTeacher;
import com.timetable.server.engine.model.input.SubjectStandard;
import com.timetable.server.engine.model.input.TeacherInfo;
import com.timetable.server.engine.model.input.TimeTableInput;

/**
 * <br />Created with IntelliJ IDEA.
 * <br />User: Subramaniam S
 * <br />Date: 4/14/2014
 * <br />Time: 8:07 PM
 */
public class InputConverter
{
	public static TimeTableInput convertRawInputToTimeTableInput(RawInput rawInput)
	{
		TimeTableInput timeTableInput = new TimeTableInput();
		RawClassGroup[] rawClassGroups = rawInput.getClassGroups();
		RawTeacher[] rawTeachers = rawInput.getTeachers();

		timeTableInput.setTotalDays(rawInput.getWorkingDays());
		timeTableInput.setTotalTeachers(rawTeachers.length);
		timeTableInput.setTotalClasses(rawClassGroups.length);
		timeTableInput.setTotalPeriods(rawInput.getPeriodPerDay());
		timeTableInput.setTeacherInfos(getTeacherInfos(rawInput.getTeachers()));
		timeTableInput.setClassGroups(getClassGroups(rawInput.getClassGroups()));
		return timeTableInput;
	}

	private static TeacherInfo[] getTeacherInfos(RawTeacher[] rawTeachers)
	{
		int teachersCount = rawTeachers.length;
		TeacherInfo[] teacherInfos = new TeacherInfo[teachersCount];
		for(int i=0; i<teachersCount; i++)
		{
			RawTeacher rawTeacher = rawTeachers[i];
			TeacherInfo teacherInfo = new TeacherInfo();
			teacherInfo.setTeacherId(rawTeacher.getId());
			teacherInfo.setWeeklyTeachingHours(rawTeacher.getHours());
			teacherInfo.setSubjectStandards(getSubjectStandards(rawTeacher.getStandardToSubjectMap()));
			teacherInfos[i] = teacherInfo;
		}
		return teacherInfos;
	}

	private static SubjectStandard[] getSubjectStandards(Map<String, String[]> standardToSubjectMap)
	{
		List<SubjectStandard> subjectStandards = new ArrayList<>();
		int counter = 0;
		for (Map.Entry<String, String[]> entry : standardToSubjectMap.entrySet())
		{
			for(String subject : entry.getValue())
			{
				SubjectStandard subjectStandard = new SubjectStandard();
				subjectStandard.setStandard(Integer.parseInt(entry.getKey()));
				subjectStandard.setSubject(subject);
				subjectStandards.add(subjectStandard);
			}
		}
		SubjectStandard[] subjectStandardsArray = new SubjectStandard[subjectStandards.size()];
		for(int i=0; i<subjectStandards.size(); i++)
			subjectStandardsArray[i] = subjectStandards.get(i);
		return subjectStandardsArray;
	}

	private static ClassGroup[] getClassGroups(RawClassGroup[] rawClassGroups)
	{
		int classGroupCount = rawClassGroups.length;
		ClassGroup[] classGroups = new ClassGroup[classGroupCount];
		for(int i=0; i<classGroupCount; i++)
		{
			RawClassGroup rawClassGroup = rawClassGroups[i];
			ClassGroup classGroup = new ClassGroup();
			classGroup.setClassGroupId(String.valueOf(rawClassGroup.getId()));
			classGroup.setStandardId(rawClassGroup.getStandardId());
			classGroup.setSubjectVsHourMap(rawClassGroup.getSubjectToHourMap());
			String[] subjects = new String[rawClassGroup.getSubjectToHourMap().entrySet().size()];
			int counter = 0;
			for (Map.Entry<String, Integer> entry : rawClassGroup.getSubjectToHourMap().entrySet())
				subjects[counter++] = entry.getKey();
			classGroup.setSubjects(subjects);
			classGroups[i] = classGroup;
		}
		return classGroups;
	}
}
