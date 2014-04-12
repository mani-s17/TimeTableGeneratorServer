package com.timetable.server.engine.model.input;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 4/7/2014
 * Time: 3:29 AM
 */
public class RawTeacher
{
	private int id;
	private int hours;
	private Map<String, String[]> standardToSubjectMap = new HashMap<>();

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getHours()
	{
		return hours;
	}

	public void setHours(int hours)
	{
		this.hours = hours;
	}

	public Map<String, String[]> getStandardToSubjectMap()
	{
		return standardToSubjectMap;
	}

	public void setStandardToSubjectMap(String standard, String[] subjects)
	{
		this.standardToSubjectMap.put(standard, subjects);
	}
}