package com.recruiter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruiter.base.ServiceBase;
import com.recruiter.domain.entity.Headhunter;
import com.recruiter.domain.repository.HeadhunterRepository;

@Service
public class HeadhunterService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HeadhunterRepository headhunterRepository;

	public List<Headhunter> getHeadhunters() {
		logger.info("calling HeadhunterService.getHeadhunters");
		final List<Headhunter> headhunters = headhunterRepository.findAll();
		logger.info("returning from HeadhunterService.getHeadhunters headhunters:{}", headhunters);
		return headhunters;
	}

	public Headhunter getHeadhunter(final Long id) {
		logger.info("calling HeadhunterService.getHeadhunter id:" + id);
		final Headhunter headhunter = headhunterRepository.findOne(id);
		logger.info("returning from HeadhunterService.getHeadhunter headhunter:{}", headhunter);
		return headhunter;
	}

	public Headhunter addHeadhunter(final Headhunter headhunter) {
		logger.info("calling HeadhunterService.addHeadhunter headhunter:{}", headhunter);
		final Headhunter createdHeadhunter = headhunterRepository.save(headhunter);
		logger.info("returning HeadhunterService.addHeadhunter createdHeadhunter:{}", createdHeadhunter);
		return createdHeadhunter;
	}

	public Headhunter updateHeadhunter(final Headhunter headhunter) {
		logger.info("calling HeadhunterService.updateHeadhunter headhunter:{}", headhunter);
		final Headhunter updatedHeadhunter = headhunterRepository.save(headhunter);
		logger.info("returning from HeadhunterService.updateHeadhunter updatedHeadhunter:{}", updatedHeadhunter);
		return updatedHeadhunter;
	}

	public void deleteHeadhunter(final Long id) {
		logger.info("calling HeadhunterService.deleteHeadhunter id:{}", id);
		headhunterRepository.delete(id);
	}
}
