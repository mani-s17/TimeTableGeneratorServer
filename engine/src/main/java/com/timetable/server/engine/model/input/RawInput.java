package com.timetable.server.engine.model.input;

/** Class which holds complete JSON RawInput which is passed from Client.
 * <br />
 * <br />Created with IntelliJ IDEA.
 * <br />User: Subramaniam S
 * <br />Date: 4/7/2014
 * <br />Time: 3:25 AM
 */
public class RawInput
{
	private int workingDays;
	private int periodPerDay;
	private RawTeacher[] teachers;
	private RawClassGroup[] classGroups;

	public int getWorkingDays()
	{
		return workingDays;
	}

	public void setWorkingDays(int workingDays)
	{
		this.workingDays = workingDays;
	}

	public int getPeriodPerDay()
	{
		return periodPerDay;
	}

	public void setPeriodPerDay(int periodPerDay)
	{
		this.periodPerDay = periodPerDay;
	}

	public RawTeacher[] getTeachers()
	{
		return teachers;
	}

	public void setTeachers(RawTeacher[] teachers)
	{
		this.teachers = teachers;
	}

	public RawClassGroup[] getClassGroups()
	{
		return classGroups;
	}

	public void setClassGroups(RawClassGroup[] classGroups)
	{
		this.classGroups = classGroups;
	}
}
