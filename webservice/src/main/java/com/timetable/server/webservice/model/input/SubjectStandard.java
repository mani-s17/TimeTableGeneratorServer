package com.timetable.server.webservice.model.input;

/**
 * A teacher can teach a subject to a particular standard. This class represents this combination.
 */
public class SubjectStandard {

	private String subject;

	private String standard;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}
}
