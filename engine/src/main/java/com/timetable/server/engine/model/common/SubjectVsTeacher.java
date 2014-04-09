package com.timetable.server.engine.model.common;

/**
 * This class will be used by the class_view of the output.
 * Basically it will tell for a particular day and period, which is the scheduled subject and by
 * which teacher.
 */
public class SubjectVsTeacher {
	private String subjectId;

	private int teacherId;

	public SubjectVsTeacher(String subjectId, int teacherId) {
		this.subjectId = subjectId;
		this.teacherId = teacherId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SubjectVsTeacher that = (SubjectVsTeacher) o;

		if (teacherId != that.teacherId) return false;
		if (subjectId != null ? !subjectId.equals(that.subjectId) : that.subjectId != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = subjectId != null ? subjectId.hashCode() : 0;
		result = 31 * result + teacherId;
		return result;
	}

	@Override
	public String toString() {
		return "SubjectVsTeacher{" +
				"subjectId='" + subjectId + '\'' +
				", teacherId=" + teacherId +
				'}';
	}
}
