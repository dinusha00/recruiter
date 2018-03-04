package com.recruiter.domain.vo;

public class Calculation {

	private final String currency;

	private final String amount;

	private final String breakdown;

	public Calculation(final String currency, final String amount, final String breakdown) {
		super();
		this.currency = currency;
		this.amount = amount;
		this.breakdown = breakdown;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Calculation [currency=");
		builder.append(currency);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", breakdown=");
		builder.append(breakdown);
		builder.append("]");
		return builder.toString();
	}

	public String getCurrency() {
		return currency;
	}

	public String getAmount() {
		return amount;
	}

	public String getBreakdown() {
		return breakdown;
	}
}
