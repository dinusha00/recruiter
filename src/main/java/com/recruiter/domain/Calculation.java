package com.recruiter.domain;

public enum Calculation {

	PERCENTAGE(1), FIXED(2);

	private final int id;

	Calculation(final int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
