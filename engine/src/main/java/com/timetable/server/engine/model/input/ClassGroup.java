package com.timetable.server.engine.model.input;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 4/7/2014
 * Time: 3:29 AM
 */
public class ClassGroup
{
	private int id;
	private int standardId;
	private Map<String, Integer> subjectToHourMap = new HashMap<>();

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getStandardId()
	{
		return standardId;
	}

	public void setStandardId(int standardId)
	{
		this.standardId = standardId;
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
