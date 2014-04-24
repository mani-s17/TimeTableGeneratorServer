package com.timetable.server.engine.model.output;

import java.util.HashMap;
import java.util.Map;

/**
 * <br />Created with IntelliJ IDEA.
 * <br />User: Subramaniam S
 * <br />Date: 4/11/2014
 * <br />Time: 11:32 PM
 */
public class TeacherView
{
	private int id;
	private Map<Integer, Map<Integer, String[]>> dayToPeriodToClassGroupSubjectMap = new HashMap<>();

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Map<Integer, Map<Integer, String[]>> getDayToPeriodToClassGroupSubjectMap()
	{
		return dayToPeriodToClassGroupSubjectMap;
	}

	public Map<Integer, String[]> getDayToPeriodToClassGroupSubjectMap(Integer day)
	{
		return dayToPeriodToClassGroupSubjectMap.get(day);
	}

	public String[] getDayToPeriodToClassGroupSubjectMap(Integer day, Integer period)
	{
		return dayToPeriodToClassGroupSubjectMap.get(day).get(period);
	}

	public void setDayToPeriodToClassGroupSubjectMap(Map<Integer, Map<Integer, String[]>> dayToPeriodToClassGroupSubjectMap)
	{
		this.dayToPeriodToClassGroupSubjectMap = dayToPeriodToClassGroupSubjectMap;
	}

	public void setDayToPeriodToClassGroupSubjectMap(Integer day, Map<Integer, String[]> periodToClassGroupSubjectMap)
	{
		this.dayToPeriodToClassGroupSubjectMap.put(day, periodToClassGroupSubjectMap);
	}

	public void setDayToPeriodToClassGroupSubjectMap(Integer day, Integer period, String[] classGroupSubjectMap)
	{
		Map<Integer, String[]> periodToClassGroupSubjectMap = this.dayToPeriodToClassGroupSubjectMap.get(day);
		if(periodToClassGroupSubjectMap == null)
		{
			periodToClassGroupSubjectMap = new HashMap<>();
			setDayToPeriodToClassGroupSubjectMap(day, periodToClassGroupSubjectMap);
		}
		periodToClassGroupSubjectMap.put(period, classGroupSubjectMap);
	}
}
