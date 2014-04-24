package com.timetable.server.engine.model.input;

/** Class which holds complete JSON Input which is passed from Client.
 * <br />
 * <br />Created with IntelliJ IDEA.
 * <br />User: Subramaniam S
 * <br />Date: 4/7/2014
 * <br />Time: 3:25 AM
 */
public class Input
{
	private int workingDays;
	private int periodPerDay;
	private Teacher[] teachers;
	private ClassGroup[] classGroups;

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

	public Teacher[] getTeachers()
	{
		return teachers;
	}

	public void setTeachers(Teacher[] teachers)
	{
		this.teachers = teachers;
	}

	public ClassGroup[] getClassGroups()
	{
		return classGroups;
	}

	public void setClassGroups(ClassGroup[] classGroups)
	{
		this.classGroups = classGroups;
	}
}
