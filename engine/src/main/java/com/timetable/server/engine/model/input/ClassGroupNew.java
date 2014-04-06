package com.timetable.server.engine.model.input;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 4/7/2014
 * Time: 3:29 AM
 */
public class ClassGroupNew
{
	private int id;
	private Map<String, Integer> subjectToHourMap = new HashMap<String, Integer>();

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Map<String, Integer> getSubjectToHourMap()
	{
		return subjectToHourMap;
	}

	public void setSubjectToHourMap(String subject, Integer hour)
	{
		this.subjectToHourMap.put(subject, hour);
	}
}
