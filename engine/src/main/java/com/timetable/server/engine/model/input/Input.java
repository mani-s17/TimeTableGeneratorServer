package com.timetable.server.engine.model.input;

/** Class which holds complete Input which is passed from Client.
 * <br />
 * <br />Created with IntelliJ IDEA.
 * <br />User: Subramaniam S
 * <br />Date: 4/7/2014
 * <br />Time: 3:25 AM
 */
public class Input
{
	private TeacherNew[] teachers;
	private ClassGroupNew[] classGroups;

	public TeacherNew[] getTeachers()
	{
		return teachers;
	}

	public void setTeachers(TeacherNew[] teachers)
	{
		this.teachers = teachers;
	}

	public ClassGroupNew[] getClassGroups()
	{
		return classGroups;
	}

	public void setClassGroups(ClassGroupNew[] classGroups)
	{
		this.classGroups = classGroups;
	}
}
