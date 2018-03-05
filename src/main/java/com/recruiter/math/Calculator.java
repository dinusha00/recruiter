package com.recruiter.math;

import java.text.NumberFormat;
import java.util.List;

import com.recruiter.domain.entity.JobTitle;

public class Calculator {
	private static final String ADDITION = "+";
	private static final String PERCENTAGE = "%";
	private static final String MULTIPLICATION = "*";

	private static class InstanceHolder {
		public static Calculator instance = new Calculator();
	}

	private Calculator() {
	}

	public static Calculator getInstance() {
		return InstanceHolder.instance;
	}

	public Double calculateFee(final int jobTitleCount, final int jobTitlePercentCount, final int jobTitlePercentFeeCount, final double jobTitleFixedFeeAamount,
			final double jobTitlePercentFeeAmount) {
		final Double jobTitleAmount = (jobTitleCount * jobTitleFixedFeeAamount)
				+ (jobTitlePercentCount * jobTitlePercentFeeCount * jobTitleFixedFeeAamount * jobTitlePercentFeeAmount / 100);
		return jobTitleAmount;
	}

	public void endBreakdown(final StringBuilder breakdown) {
		breakdown.append(")");
	}

	public void startBreakdown(final String feeCalculation, final StringBuilder breakdown) {
		breakdown.append("(");
		breakdown.append(feeCalculation);
		breakdown.append(" = ");
	}

	public void generateBreakdown(final List<JobTitle> jobTitles, final StringBuilder breakdown, final NumberFormat formatter, int count, final String jobTitleName,
			int jobTitleCount, double jobTitleFixedFeeAamount, int jobTitlePercentCount, int jobTitlePercentFeeCount, double jobTitlePercentFeeAmount) {
		breakdown.append(jobTitleName);
		breakdown.append(": [");
		breakdown.append(jobTitleCount);
		breakdown.append(MULTIPLICATION);
		breakdown.append(formatter.format(jobTitleFixedFeeAamount));
		breakdown.append(" ");
		breakdown.append(ADDITION);
		breakdown.append(" (");
		breakdown.append(jobTitlePercentCount * jobTitlePercentFeeCount);
		breakdown.append(MULTIPLICATION);
		breakdown.append(formatter.format(jobTitleFixedFeeAamount));
		breakdown.append(")");
		breakdown.append(MULTIPLICATION);
		breakdown.append(formatter.format(jobTitlePercentFeeAmount));
		breakdown.append(PERCENTAGE);
		breakdown.append("]");
		if (count < jobTitles.size()) {
			breakdown.append(" ");
			breakdown.append(ADDITION);
			breakdown.append(" ");
		}
	}
}
