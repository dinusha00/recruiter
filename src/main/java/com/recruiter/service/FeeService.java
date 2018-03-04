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
import com.recruiter.domain.repository.CandidateRepository;
import com.recruiter.domain.repository.FeeRepository;
import com.recruiter.domain.vo.Calculation;

@Service
public class FeeService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private FeeRepository feeRepository;

	public Calculation calculate(final Long id) {
		logger.info("calling FeeService.calculate id:" + id);
		final NumberFormat formatter = new DecimalFormat(currencyFormat);

		// get type1 (mason) fee info
		final List<Fee> feesForTypeMason = feeRepository.findByJobtitleid(1);
		final Fee feeFixedForTypeMason = feesForTypeMason.get(0);
		final Fee feePercentageForTypeMason = feesForTypeMason.get(1);
		logger.debug("feeFixedForTypeMason:{}", feeFixedForTypeMason);
		logger.debug("feePercentageForTypeMason:{}", feePercentageForTypeMason);

		// get type2 (carpenter) fee info
		final List<Fee> feesForTypeCarpenter = feeRepository.findByJobtitleid(2);
		final Fee feeFixedForTypeCarpenter = feesForTypeCarpenter.get(0);
		final Fee feePercentageForTypeCarpenter = feesForTypeCarpenter.get(1);
		logger.debug("feeFixedForTypeCarpenter:{}", feeFixedForTypeCarpenter);
		logger.debug("feePercentageForTypeCarpenter:{}", feePercentageForTypeCarpenter);

		final List<Candidate> candidatesTypeMason = candidateRepository.findByHeadhunterid(id); // TODO: Add the jobtitleid filter and monthly filter
		logger.debug("candidatesTypeMason:{}", candidatesTypeMason);
		final List<Candidate> candidatesTypeCarpenter = candidateRepository.findByHeadhunterid(id); // TODO: Add the jobtitleid filter and monthly filter
		logger.debug("candidatesTypeCarpenter:{}", candidatesTypeCarpenter);

		final int countForTypeMason = candidatesTypeMason.size();
		logger.debug("countForTypeMason:{}", countForTypeMason);
		final int countForTypeCarpenter = candidatesTypeCarpenter.size();
		logger.debug("countForTypeCarpenter:{}", countForTypeCarpenter);

		final Double amountForTypeMason = (countForTypeMason * feeFixedForTypeMason.getAmount())
				+ (Math.floor(countForTypeMason / feePercentageForTypeMason.getCount()) * feePercentageForTypeMason.getAmount());

		final Double amountForTypeCarpenter = (countForTypeCarpenter * feeFixedForTypeCarpenter.getAmount())
				+ (Math.floor(countForTypeCarpenter / feePercentageForTypeCarpenter.getCount()) * feePercentageForTypeCarpenter.getAmount());

		final Double amount = amountForTypeMason + amountForTypeCarpenter;
		final StringBuilder breakdown = buildBreakdown(feeFixedForTypeMason, feePercentageForTypeMason, countForTypeMason, amountForTypeMason, feeFixedForTypeCarpenter,
				feePercentageForTypeCarpenter, countForTypeCarpenter, amountForTypeCarpenter);

		logger.info("amount:{}", amount);
		logger.info("breakdown:{}", breakdown);

		final Calculation calculation = new Calculation(currencyCode, formatter.format(amount), breakdown.toString());
		logger.info("returning from FeeService.calculate calculation:{}", calculation);
		return calculation;
	}

	private StringBuilder buildBreakdown(final Fee feeFixedForTypeMason, final Fee feePercentageForTypeMason, final int countForTypeMason, final Double amountForTypeMason,
			final Fee feeFixedForTypeCarpenter, final Fee feePercentageForTypeCarpenter, final int countForTypeCarpenter, final Double amountForTypeCarpenter) {
		final StringBuilder breakdown = new StringBuilder();
		breakdown.append("--------------------------").append("\n");
		breakdown.append("fee calculation(mason)").append("\n");
		breakdown.append("--------------------------").append("\n");
		breakdown.append("count for type mason:" + countForTypeMason).append("\n");
		breakdown.append("fixed fee for mason:" + feeFixedForTypeMason.getAmount()).append("\n");
		breakdown.append("% fee for mason:" + feePercentageForTypeMason.getAmount()).append("\n");
		breakdown.append("% total amount for type mason:" + amountForTypeMason).append("\n");

		breakdown.append("--------------------------").append("\n");
		breakdown.append("fee calculation(carpenter)").append("\n");
		breakdown.append("--------------------------").append("\n");
		breakdown.append("count for type carpenter:" + countForTypeCarpenter).append("\n");
		breakdown.append("fixed fee for carpenter:" + feeFixedForTypeCarpenter.getAmount()).append("\n");
		breakdown.append("% fee for carpenter:" + feePercentageForTypeCarpenter.getAmount()).append("\n");
		breakdown.append("% total amount for type carpenter:" + amountForTypeCarpenter).append("\n");

		breakdown.append("--------------------------").append("\n");
		breakdown.append("fee calculation(total)").append("\n");
		breakdown.append("--------------------------").append("\n");
		breakdown.append("final total amount:" + feePercentageForTypeMason).append("+").append(feePercentageForTypeCarpenter).append("\n");
		return breakdown;
	}
}
