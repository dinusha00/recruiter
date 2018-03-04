package com.recruiter.domain.vo;

public enum CalculationType {

	FIXED(1), PERCENTAGE(2);

	private final int id;

	CalculationType(final int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
