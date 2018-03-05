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

		final List<Candidate> masonCandidates = candidateRepository.findByHeadhunterIdAndJobTitleIdAndRecruited(id, 1L, true);
		final List<Fee> masonFeeType = feeRepository.findByJobTitleId(1);
		final Fee masonFixedFee = masonFeeType.get(0);
		final Fee masonPercentFee = masonFeeType.get(1);
		final int masonCount = masonCandidates.size();
		final int masonPercentCount = new Double(Math.floor(masonCount / masonPercentFee.getCount())).intValue();

		final Double masonAmount = (masonCount * masonFixedFee.getAmount())
				+ (masonPercentCount * masonPercentFee.getCount() * masonFixedFee.getAmount() * masonPercentFee.getAmount() / 100);

		final List<Candidate> carpenterCandidates = candidateRepository.findByHeadhunterIdAndJobTitleIdAndRecruited(id, 2L, true);
		final List<Fee> carpenterFeeType = feeRepository.findByJobTitleId(2);
		final Fee carpenterFixedFee = carpenterFeeType.get(0);
		final Fee carpenterPercentFee = carpenterFeeType.get(1);
		final int carpenterCount = carpenterCandidates.size();
		final int carpenterPercentCount = new Double(Math.floor(carpenterCount / carpenterPercentFee.getCount())).intValue();
		final Double carpenterAmount = (carpenterCount * carpenterFixedFee.getAmount())
				+ (carpenterPercentCount * carpenterPercentFee.getCount() * carpenterFixedFee.getAmount() * carpenterPercentFee.getAmount() / 100);

		logger.info("masonAmount:{} carpenterAmount:{}", masonAmount, carpenterAmount);
		final Double amount = masonAmount + carpenterAmount;

		final StringBuilder breakdown = new StringBuilder();
		breakdown.append("(");
		breakdown.append("Calculation = ");
		breakdown.append("Masons: [" + masonCount + "*" + formatter.format(masonFixedFee.getAmount()) + " + (" + masonPercentCount * masonPercentFee.getCount() + "*"
				+ formatter.format(masonFixedFee.getAmount()) + ")*" + formatter.format(masonPercentFee.getAmount()) + "%]");
		breakdown.append(" + ");
		breakdown.append("Carpenters: [" + carpenterCount + "*" + formatter.format(carpenterFixedFee.getAmount()) + " + (" + carpenterPercentCount * carpenterPercentFee.getCount()
				+ "*" + formatter.format(carpenterFixedFee.getAmount()) + ")*" + formatter.format(carpenterPercentFee.getAmount()) + "%]");
		breakdown.append(")");

		final Calculation calculation = new Calculation(currencyCode, formatter.format(amount), breakdown.toString());
		logger.info("returning from FeeService.calculate calculation:{}", calculation);
		return calculation;
	}
}
