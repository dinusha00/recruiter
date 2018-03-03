package com.recruiter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.recruiter.base.ServiceBase;
import com.recruiter.domain.mapping.Headhunter;
import com.recruiter.domain.repository.HeadhunterRepository;

@RestController
@RequestMapping("/headhunter")
public class HeadhunterService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HeadhunterRepository headhunterRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Headhunter> getHeadhunters() {
		logger.info("calling HeadhunterService.getHeadhunters");
		final List<Headhunter> headhunters = headhunterRepository.findAll();
		logger.info("returning from HeadhunterService.getHeadhunters headhunters.size():{}", headhunters.size());
		return headhunters;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Headhunter getHeadhunter(@PathVariable final Long id) {
		logger.info("calling HeadhunterService.getHeadhunter id:" + id);
		final Headhunter headhunter = headhunterRepository.findOne(id);
		logger.info("returning from HeadhunterService.getHeadhunter headhunter:{}", headhunter);
		return headhunter;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Headhunter addHeadhunter(@RequestBody final Headhunter headhunter) {
		logger.info("calling HeadhunterService.addHeadhunter headhunter:{}", headhunter);
		final Headhunter createdHeadhunter = headhunterRepository.save(headhunter);
		return createdHeadhunter;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Headhunter updateHeadhunter(@RequestBody final Headhunter headhunter) {
		logger.info("calling HeadhunterService.updateHeadhunter headhunter:{}", headhunter);
		final Headhunter updatedHeadhunter = headhunterRepository.save(headhunter);
		return updatedHeadhunter;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteHeadhunter(@PathVariable final Long id) {
		logger.info("calling HeadhunterService.deleteHeadhunter id:{}", id);
		headhunterRepository.delete(id);
	}
}
