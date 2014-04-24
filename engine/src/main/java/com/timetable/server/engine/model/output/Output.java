package com.timetable.server.engine.model.output;

/** Class which holds final Output which will be converted as JSON.
 * <br />
 * <br />Created with IntelliJ IDEA.
 * <br />User: Subramaniam S
 * <br />Date: 4/11/2014
 * <br />Time: 11:31 PM
 */
public class Output
{
	private TeacherView[] teachersView;
	private ClassGroupView[] classGroupsView;

	public TeacherView[] getTeachersView()
	{
		return teachersView;
	}

	public void setTeachersView(TeacherView[] teachersView)
	{
		this.teachersView = teachersView;
	}

	public ClassGroupView[] getClassGroupsView()
	{
		return classGroupsView;
	}

	public void setClassGroupsView(ClassGroupView[] classGroupsView)
	{
		this.classGroupsView = classGroupsView;
	}
}
