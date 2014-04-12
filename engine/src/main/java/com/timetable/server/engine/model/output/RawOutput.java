package com.timetable.server.engine.model.output;

/** Class which holds final Output which will be converted as JSON.
 * <br />
 * <br />Created with IntelliJ IDEA.
 * <br />User: Subramaniam S
 * <br />Date: 4/11/2014
 * <br />Time: 11:31 PM
 */
public class RawOutput
{
	private RawTeacherView[] teachersView;
	private RawClassGroupView[] classGroupsView;

	public RawTeacherView[] getTeachersView()
	{
		return teachersView;
	}

	public void setTeachersView(RawTeacherView[] teachersView)
	{
		this.teachersView = teachersView;
	}

	public RawClassGroupView[] getClassGroupsView()
	{
		return classGroupsView;
	}

	public void setClassGroupsView(RawClassGroupView[] classGroupsView)
	{
		this.classGroupsView = classGroupsView;
	}
}
