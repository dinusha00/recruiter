package com.recruiter.domain.vo;

public enum CalculationType {

	PERCENTAGE(1), FIXED(2);

	private final int id;

	CalculationType(final int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
