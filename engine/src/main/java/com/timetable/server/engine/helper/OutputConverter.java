package com.timetable.server.engine.helper;

import java.util.HashMap;
import java.util.Map;

import com.timetable.server.engine.model.common.ClassGroupVsSubject;
import com.timetable.server.engine.model.common.ClassView;
import com.timetable.server.engine.model.common.SubjectVsTeacher;
import com.timetable.server.engine.model.common.TeacherView;
import com.timetable.server.engine.model.output.RawClassGroupView;
import com.timetable.server.engine.model.output.RawOutput;
import com.timetable.server.engine.model.output.RawTeacherView;
import com.timetable.server.engine.model.output.SampleOutput;

/**
 * <br />Created with IntelliJ IDEA.
 * <br />User: Subramaniam S
 * <br />Date: 4/14/2014
 * <br />Time: 8:19 PM
 */
public class OutputConverter
{
	public static RawOutput convertSampleOutputToRawOutput(SampleOutput sampleOutput, int workingDays, int periodPerDay)
	{
		RawOutput rawOutput = new RawOutput();
		rawOutput.setTeachersView(convertTeacherViewToRaw(sampleOutput.getTeacherViews(), workingDays, periodPerDay));
		rawOutput.setClassGroupsView(convertClassViewToRaw(sampleOutput.getClassViews(), workingDays, periodPerDay));
		return rawOutput;
	}

	private static RawTeacherView[] convertTeacherViewToRaw(TeacherView[] teacherViews, int workingDays, int periodPerDay)
	{
		RawTeacherView[] rawTeacherViews = new RawTeacherView[teacherViews.length];
		for(int i=0; i<teacherViews.length; i++)
		{
			TeacherView teacherView = teacherViews[i];
			RawTeacherView rawTeacherView = new RawTeacherView();
			rawTeacherView.setId(teacherView.getTeacherId());
			Map<Integer, Map<Integer, String[]>> dayToPeriodToClassGroupSubjectMap = new HashMap<>();

			for(int day=1; i<=workingDays; day++)
			{
				Map<Integer, String[]> periodToClassGroupSubjectMap = new HashMap<>();
				for(int period=1; i<=periodPerDay; i++)
				{
					ClassGroupVsSubject classGroupVsSubject = teacherView.getClassGroupVsSubject(day, period);
					if (classGroupVsSubject != null)
						periodToClassGroupSubjectMap.put(period, new String[]{classGroupVsSubject.getClassGroupId(), classGroupVsSubject.getSubjectId()});
				}
				dayToPeriodToClassGroupSubjectMap.put(day, periodToClassGroupSubjectMap);
			}

			rawTeacherView.setDayToPeriodToClassGroupSubjectMap(dayToPeriodToClassGroupSubjectMap);
		}
		return rawTeacherViews;
	}

	private static RawClassGroupView[] convertClassViewToRaw(ClassView[] classViews, int workingDays, int periodPerDay)
	{
		RawClassGroupView[] rawClassGroupsView = new RawClassGroupView[classViews.length];
		for(int i=0; i<classViews.length; i++)
		{
			ClassView classView = classViews[i];
			RawClassGroupView rawClassGroupView = new RawClassGroupView();
			rawClassGroupView.setId(Integer.parseInt(classView.getClassGroupId()));
			Map<Integer, String[]> dayToSequencedPeriodsMap = new HashMap<>();
			for(int day=1; i<=workingDays; day++)
			{
				String[] subjects = new String[periodPerDay];
				for(int period=1; i<=periodPerDay; i++)
				{
					SubjectVsTeacher subjectVsTeacher = classView.getSubjectVsTeacher(day, period);
					if(subjectVsTeacher != null)
						subjects[period-1] = subjectVsTeacher.getSubjectId();
				}
				dayToSequencedPeriodsMap.put(day, subjects);
			}
			rawClassGroupView.setDayToSequencedPeriodsMap(dayToSequencedPeriodsMap);
		}
		return rawClassGroupsView;
	}
}
