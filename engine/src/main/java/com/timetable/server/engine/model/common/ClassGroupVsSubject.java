package com.timetable.server.engine.model.common;

/**
 * class to be used by the teacher view of the time table output.
 * It would tell for a day and period, which class_group and which subject the teacher is scheduled
 * to teach.
 */
public class ClassGroupVsSubject {
	private String classGroupId;

	private String subjectId;

	public ClassGroupVsSubject(String classGroupId, String subjectId) {
		this.classGroupId = classGroupId;
		this.subjectId = subjectId;
	}

	public String getClassGroupId() {
		return classGroupId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ClassGroupVsSubject that = (ClassGroupVsSubject) o;

		if (classGroupId != null ? !classGroupId.equals(that.classGroupId) : that.classGroupId != null) return false;
		if (subjectId != null ? !subjectId.equals(that.subjectId) : that.subjectId != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = classGroupId != null ? classGroupId.hashCode() : 0;
		result = 31 * result + (subjectId != null ? subjectId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ClassGroupVsSubject{" +
				"classGroupId='" + classGroupId + '\'' +
				", subjectId='" + subjectId + '\'' +
				'}';
	}
}
