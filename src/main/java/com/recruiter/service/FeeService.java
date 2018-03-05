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
import com.recruiter.math.Calculator;

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
		Calculator.getInstance().startBreakdown(feeCalculation, breakdown);
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
			final Double jobTitleAmount = Calculator.getInstance().calculateFee(jobTitleCount, jobTitlePercentCount, jobTitlePercentFeeCount, jobTitleFixedFeeAamount,
					jobTitlePercentFeeAmount);
			logger.info("jobTitle.getName:{} jobTitleAmount:{}", jobTitleName, jobTitleAmount);
			total += jobTitleAmount;
			Calculator.getInstance().generateBreakdown(jobTitles, breakdown, formatter, count, jobTitleName, jobTitleCount, jobTitleFixedFeeAamount, jobTitlePercentCount,
					jobTitlePercentFeeCount, jobTitlePercentFeeAmount);
		}
		Calculator.getInstance().endBreakdown(breakdown);
		final Calculation calculation = new Calculation(currencyCode, formatter.format(total), breakdown.toString());
		logger.info("returning from FeeService.calculate calculation:{}", calculation);
		return calculation;
	}
}
