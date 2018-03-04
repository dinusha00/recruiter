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

import com.recruiter.domain.entity.Candidate;
import com.recruiter.domain.entity.Headhunter;
import com.recruiter.domain.vo.Calculation;
import com.recruiter.service.CandidateService;
import com.recruiter.service.HeadhunterService;

@RestController
@RequestMapping("/headhunter")
public class HeadhunterController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HeadhunterService headhunterService;

	@Autowired
	private CandidateService candidateService;

	@GetMapping
	public List<Headhunter> readHeadhunters() {
		logger.info("calling HeadhunterController.readHeadhunters");
		final List<Headhunter> headhunters = headhunterService.readHeadhunters();
		logger.info("returning from HeadhunterController.readHeadhunters headhunters.size():{}", headhunters.size());
		return headhunters;
	}

	@GetMapping(value = "/{id}")
	public Headhunter readHeadhunter(@PathVariable final Long id) {
		logger.info("calling HeadhunterController.readHeadhunter id:" + id);
		final Headhunter headhunter = headhunterService.readHeadhunter(id);
		logger.info("returning from HeadhunterController.readHeadhunter headhunter:{}", headhunter);
		return headhunter;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Headhunter createHeadhunter(@RequestBody final Headhunter headhunter) {
		logger.info("calling HeadhunterController.createHeadhunter headhunter:{}", headhunter);
		final Headhunter createdHeadhunter = headhunterService.createHeadhunter(headhunter);
		logger.info("returning from HeadhunterController.createHeadhunter createdHeadhunter:{}", createdHeadhunter);
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

	@GetMapping(value = "/{id}/candidates")
	public List<Candidate> readHeadhunterCandidates(@PathVariable final Long id) {
		logger.info("calling HeadhunterController.readHeadhunterCandidates id:" + id);
		final List<Candidate> candidates = candidateService.readHeadhunterCandidates(id);
		logger.info("returning from HeadhunterController.readHeadhunterCandidates candidates:{}", candidates);
		return candidates;
	}

	@GetMapping(value = "/{id}/calculate")
	public Calculation calculate(@PathVariable final Long id) {
		logger.info("calling HeadhunterController.calculate id:" + id);
		final Calculation calculation = headhunterService.calculate(id);
		logger.info("returning from HeadhunterController.calculate calculation:{}", calculation);
		return calculation;
	}
}
