package com.recruiter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruiter.base.ServiceBase;
import com.recruiter.domain.entity.Headhunter;
import com.recruiter.domain.repository.HeadhunterRepository;
import com.recruiter.domain.vo.Calculation;

@Service
public class HeadhunterService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HeadhunterRepository headhunterRepository;

	@Autowired
	private FeeService feeService;

	public List<Headhunter> readHeadhunters() {
		logger.info("calling HeadhunterService.readHeadhunters");
		final List<Headhunter> headhunters = headhunterRepository.findAll();
		logger.info("returning from HeadhunterService.readHeadhunters headhunters:{}", headhunters);
		return headhunters;
	}

	public Headhunter readHeadhunter(final Long id) {
		logger.info("calling HeadhunterService.readHeadhunter id:" + id);
		final Headhunter headhunter = headhunterRepository.findOne(id);
		logger.info("returning from HeadhunterService.readHeadhunter headhunter:{}", headhunter);
		return headhunter;
	}

	public Headhunter createHeadhunter(final Headhunter headhunter) {
		logger.info("calling HeadhunterService.createHeadhunter headhunter:{}", headhunter);
		commonValidation(headhunter);
		final Headhunter createdHeadhunter = headhunterRepository.save(headhunter);
		logger.info("returning HeadhunterService.createHeadhunter createdHeadhunter:{}", createdHeadhunter);
		return createdHeadhunter;
	}

	private void commonValidation(final Headhunter headhunter) {
		if (headhunter == null || null == headhunter.getName() || headhunter.getName().isEmpty()) {
			throw new IllegalArgumentException(msgHeadhunterCannotBeEmpty);
		} else if (headhunterRepository.findByName(headhunter.getName()) != null) {
			throw new IllegalArgumentException(msgHeadhunterAlreadyExists);
		}
	}

	public Headhunter updateHeadhunter(final Headhunter headhunter) {
		logger.info("calling HeadhunterService.updateHeadhunter headhunter:{}", headhunter);
		commonValidation(headhunter);
		if (headhunterRepository.findOne(headhunter.getId()) == null) {
			throw new IllegalArgumentException(msgHeadhunterDoesnotExists);
		}
		final Headhunter updatedHeadhunter = headhunterRepository.save(headhunter);
		logger.info("returning from HeadhunterService.updateHeadhunter updatedHeadhunter:{}", updatedHeadhunter);
		return updatedHeadhunter;
	}

	public void deleteHeadhunter(final Long id) {
		logger.info("calling HeadhunterService.deleteHeadhunter id:{}", id);
		headhunterRepository.delete(id);
	}

	public Calculation calculate(final Long id) {
		logger.info("calling HeadhunterService.calculate id:" + id);
		final Calculation calculation = feeService.calculate(id);
		logger.info("returning from HeadhunterService.calculate calculation:{}", calculation);
		return calculation;
	}
}
