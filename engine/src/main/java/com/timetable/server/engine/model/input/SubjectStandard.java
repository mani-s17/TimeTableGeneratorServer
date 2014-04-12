package com.timetable.server.engine.model.input;

/**
 * A teacher can teach a subject to a particular standard. This class represents this combination.
 */
public class SubjectStandard {

	private String subject;

	private int standard;

	public SubjectStandard() {};

	public SubjectStandard(String subject, int standard) {
		this.subject = subject;
		this.standard = standard;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	@Override
	public String toString() {
		return "SubjectStandard {" +
				"subject='" + subject + '\'' +
				", standard='" + standard + '\'' +
				'}';
	}
}
