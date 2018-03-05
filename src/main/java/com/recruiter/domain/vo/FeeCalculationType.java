package com.recruiter.domain.vo;

import java.util.HashMap;
import java.util.Map;

public enum FeeCalculationType {

	FIXED(1), PERCENTAGE(2);

	private final int value;

	private final static Map<Integer, FeeCalculationType> values = new HashMap<>();

	private FeeCalculationType(int value) {
		this.value = value;
	}

	public int toValue() {
		return value;
	}

	public static FeeCalculationType fromValue(final int value) {
		return values.get(value);
	}

	static {
		values.put(FIXED.value, FIXED);
		values.put(PERCENTAGE.value, PERCENTAGE);
	}
}
