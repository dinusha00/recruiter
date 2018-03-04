package com.recruiter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.recruiter.base.ServiceBase;
import com.recruiter.domain.entity.Headhunter;
import com.recruiter.service.HeadhunterService;

@RestController
@RequestMapping("/headhunter")
public class HeadhunterController extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HeadhunterService headhunterService;

	@GetMapping
	public List<Headhunter> getHeadhunters() {
		logger.info("calling HeadhunterController.getHeadhunters");
		final List<Headhunter> headhunters = headhunterService.getHeadhunters();
		logger.info("returning from HeadhunterController.getHeadhunters headhunters.size():{}", headhunters.size());
		return headhunters;
	}

	@GetMapping(value = "/{id}")
	public Headhunter getHeadhunter(@PathVariable final Long id) {
		logger.info("calling HeadhunterController.getHeadhunter id:" + id);
		final Headhunter headhunter = headhunterService.getHeadhunter(id);
		logger.info("returning from HeadhunterController.getHeadhunter headhunter:{}", headhunter);
		return headhunter;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Headhunter addHeadhunter(@RequestBody final Headhunter headhunter) {
		logger.info("calling HeadhunterController.addHeadhunter headhunter:{}", headhunter);
		final Headhunter createdHeadhunter = headhunterService.addHeadhunter(headhunter);
		logger.info("returning from HeadhunterController.addHeadhunter createdHeadhunter:{}", createdHeadhunter);
		return createdHeadhunter;
	}

	@PutMapping
	public Headhunter updateHeadhunter(@RequestBody final Headhunter headhunter) {
		logger.info("calling HeadhunterController.updateHeadhunter headhunter:{}", headhunter);
		final Headhunter updatedHeadhunter = headhunterService.updateHeadhunter(headhunter);
		logger.info("returning from HeadhunterController.updateHeadhunter updatedHeadhunter:{}", headhunter);
		return updatedHeadhunter;
	}

	@DeleteMapping(value = "/{id}")
	public void deleteHeadhunter(@PathVariable final Long id) {
		logger.info("calling HeadhunterController.deleteHeadhunter id:{}", id);
		headhunterService.deleteHeadhunter(id);
	}
}
