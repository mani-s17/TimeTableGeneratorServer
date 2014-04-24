package com.timetable.server.engine.model.output;

import java.util.HashMap;
import java.util.Map;

/**
 * <br />Created with IntelliJ IDEA.
 * <br />User: Subramaniam S
 * <br />Date: 4/11/2014
 * <br />Time: 11:35 PM
 */
public class ClassGroupView
{
	private int id;
	private Map<Integer,String[]> dayToSequencedPeriodsMap = new HashMap<>();

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Map<Integer, String[]> getDayToSequencedPeriodsMap()
	{
		return dayToSequencedPeriodsMap;
	}

	public String[] getDayToSequencedPeriodsMap(Integer day)
	{
		return dayToSequencedPeriodsMap.get(day);
	}

	public void setDayToSequencedPeriodsMap(Map<Integer, String[]> dayToSequencedPeriodsMap)
	{
		this.dayToSequencedPeriodsMap = dayToSequencedPeriodsMap;
	}

	public void setDayToSequencedPeriodMap(Integer day, String[] sequencedPeriods)
	{
		this.dayToSequencedPeriodsMap.put(day, sequencedPeriods);
	}
}
