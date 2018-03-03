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
import com.recruiter.domain.mapping.HeadHunter;
import com.recruiter.domain.repository.HeadHunterRepository;

@RestController
@RequestMapping("/headhunter")
public class HeadHunterService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HeadHunterRepository headHunterRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<HeadHunter> getHeadHunters() {
		logger.info("calling HeadHunterService.getHeadHunters");
		final List<HeadHunter> headHunters = headHunterRepository.findAll();
		logger.info("returning from HeadHunterService.getHeadHunters headHunters.size():{}", headHunters.size());
		return headHunters;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public HeadHunter getHeadHunter(@PathVariable final Long id) {
		logger.info("calling HeadHunterService.getHeadHunter id:" + id);
		final HeadHunter headHunter = headHunterRepository.findOne(id);
		logger.info("returning from HeadHunterService.getHeadHunter headHunter:{}", headHunter);
		return headHunter;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public HeadHunter addHeadHunter(@RequestBody final HeadHunter headHunter) {
		logger.info("calling HeadHunterService.addHeadHunter headHunter:{}", headHunter);
		final HeadHunter createdHeadHunter = headHunterRepository.save(headHunter);
		return createdHeadHunter;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public HeadHunter updateHeadHunter(@RequestBody final HeadHunter headHunter) {
		logger.info("calling HeadHunterService.updateHeadHunter headHunter:{}", headHunter);
		final HeadHunter updatedHeadHunter = headHunterRepository.save(headHunter);
		return updatedHeadHunter;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteHeadHunter(@PathVariable final Long id) {
		logger.info("calling HeadHunterService.deleteHeadHunter id:{}", id);
		headHunterRepository.delete(id);
	}
}
