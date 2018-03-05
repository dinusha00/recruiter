package com.recruiter.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruiter.base.ServiceBase;
import com.recruiter.domain.entity.Candidate;
import com.recruiter.domain.entity.Fee;
import com.recruiter.domain.entity.JobTitle;
import com.recruiter.domain.repository.CandidateRepository;
import com.recruiter.domain.repository.FeeRepository;
import com.recruiter.domain.repository.JobTitleRepository;
import com.recruiter.domain.vo.Calculation;
import com.recruiter.domain.vo.FeeCalculationType;

@Service
public class FeeService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private JobTitleRepository jobTitleRepository;

	@Autowired
	private FeeRepository feeRepository;

	public Calculation calculate(final Long headhunterId) {
		logger.info("calling FeeService.calculate headhunterId:" + headhunterId);
		final List<JobTitle> jobTitles = jobTitleRepository.findAll();
		final StringBuilder breakdown = new StringBuilder();
		final NumberFormat formatter = new DecimalFormat(currencyFormat);
		Double total = 0.0;
		int count = 0;
		startBreakdown(breakdown);
		for (final JobTitle jobTitle : jobTitles) {
			count++;
			final Long jobTitleId = jobTitle.getId();
			final String jobTitleName = jobTitle.getName();
			int jobTitleCount = 0, jobTitlePercentCount = 0, jobTitlePercentFeeCount = 0;
			double jobTitleFixedFeeAamount = 0, jobTitlePercentFeeAmount = 0;
			final List<Candidate> jobTitleCandidates = candidateRepository.findByHeadhunterIdAndJobTitleIdAndRecruited(headhunterId, jobTitleId, true);
			if (false == jobTitleCandidates.isEmpty()) {
				jobTitleCount = jobTitleCandidates.size();
			}
			final Fee jobTitleFixedFee = feeRepository.findByJobTitleIdAndType(jobTitleId, FeeCalculationType.FIXED.toValue());
			final Fee jobTitlePercentFee = feeRepository.findByJobTitleIdAndType(jobTitleId, FeeCalculationType.PERCENTAGE.toValue());
			jobTitlePercentFeeCount = jobTitlePercentFee.getCount();
			jobTitlePercentCount = new Double(Math.floor(jobTitleCount / jobTitlePercentFeeCount)).intValue();
			jobTitleFixedFeeAamount = jobTitleFixedFee.getAmount();
			jobTitlePercentFeeAmount = jobTitlePercentFee.getAmount();
			final Double jobTitleAmount = calculateJobTitleAmount(jobTitleCount, jobTitlePercentCount, jobTitlePercentFeeCount, jobTitleFixedFeeAamount, jobTitlePercentFeeAmount);
			logger.info("jobTitle.getName:{} jobTitleAmount:{}", jobTitleName, jobTitleAmount);
			total += jobTitleAmount;
			generateBreakdown(jobTitles, breakdown, formatter, count, jobTitleName, jobTitleCount, jobTitleFixedFeeAamount, jobTitlePercentCount, jobTitlePercentFeeCount,
					jobTitlePercentFeeAmount);
		}
		endBreakdown(breakdown);
		final Calculation calculation = new Calculation(currencyCode, formatter.format(total), breakdown.toString());
		logger.info("returning from FeeService.calculate calculation:{}", calculation);
		return calculation;
	}

	private Double calculateJobTitleAmount(final int jobTitleCount, final int jobTitlePercentCount, final int jobTitlePercentFeeCount, final double jobTitleFixedFeeAamount,
			final double jobTitlePercentFeeAmount) {
		final Double jobTitleAmount = (jobTitleCount * jobTitleFixedFeeAamount)
				+ (jobTitlePercentCount * jobTitlePercentFeeCount * jobTitleFixedFeeAamount * jobTitlePercentFeeAmount / 100);
		return jobTitleAmount;
	}

	private void endBreakdown(final StringBuilder breakdown) {
		breakdown.append(")");
	}

	private void startBreakdown(final StringBuilder breakdown) {
		breakdown.append("(");
		breakdown.append(feeCalculation);
		breakdown.append(" = ");
	}

	private void generateBreakdown(final List<JobTitle> jobTitles, final StringBuilder breakdown, final NumberFormat formatter, int count, final String jobTitleName,
			int jobTitleCount, double jobTitleFixedFeeAamount, int jobTitlePercentCount, int jobTitlePercentFeeCount, double jobTitlePercentFeeAmount) {
		breakdown.append(jobTitleName);
		breakdown.append(": [");
		breakdown.append(jobTitleCount);
		breakdown.append("*");
		breakdown.append(formatter.format(jobTitleFixedFeeAamount));
		breakdown.append(" + (");
		breakdown.append(jobTitlePercentCount * jobTitlePercentFeeCount);
		breakdown.append("*");
		breakdown.append(formatter.format(jobTitleFixedFeeAamount));
		breakdown.append(")*");
		breakdown.append(formatter.format(jobTitlePercentFeeAmount));
		breakdown.append("%]");
		if (count < jobTitles.size()) {
			breakdown.append(" + ");
		}
	}
}
