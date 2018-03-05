package com.recruiter.domain.vo;

import java.util.HashMap;
import java.util.Map;

public enum CalculationType {

	FIXED(1), PERCENTAGE(2);

	private final Long value;
	private final static Map<Long, CalculationType> values = new HashMap<>();

	private CalculationType(long value) {
		this.value = value;
	}

	public Long toValue() {
		return value;
	}

	public static CalculationType fromValue(final Long value) {
		return values.get(value);
	}

	static {
		values.put(FIXED.value, FIXED);
		values.put(PERCENTAGE.value, PERCENTAGE);
	}
}
