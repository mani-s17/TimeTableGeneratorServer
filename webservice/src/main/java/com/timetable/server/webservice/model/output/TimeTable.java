package com.timetable.server.webservice.model.output;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 3/29/2014
 * Time: 11:01 PM
 */
public class TimeTable
{
	private String teacherId;
	private String[] classes;

	public String getTeacherId()
	{
		return teacherId;
	}

	public void setTeacherId(String teacherId)
	{
		this.teacherId = teacherId;
	}

	public String[] getClasses()
	{
		return classes;
	}

	public void setClasses(String[] classes)
	{
		this.classes = classes;
	}
}
