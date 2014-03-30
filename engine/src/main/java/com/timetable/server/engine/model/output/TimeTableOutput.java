package com.timetable.server.engine.model.output;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 3/30/2014
 * Time: 8:15 AM
 */
public class TimeTableOutput
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
