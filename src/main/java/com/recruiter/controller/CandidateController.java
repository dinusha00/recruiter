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
import com.recruiter.service.CandidateService;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CandidateService candidateService;

	@GetMapping
	public List<Candidate> readCandidates() {
		logger.info("calling CandidateController.readCandidates");
		final List<Candidate> candidates = candidateService.readCandidates();
		logger.info("returning from CandidateController.readCandidates candidates.size():{}", candidates.size());
		return candidates;
	}

	@GetMapping(value = "/{id}")
	public Candidate readCandidate(@PathVariable final Long id) {
		logger.info("calling CandidateController.readCandidate id:" + id);
		final Candidate candidate = candidateService.readCandidate(id);
		logger.info("returning from CandidateController.readCandidate candidate:{}", candidate);
		return candidate;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Candidate createCandidate(@RequestBody final Candidate candidate) {
		logger.info("calling CandidateController.createCandidate candidate:{}", candidate);
		final Candidate createdCandidate = candidateService.createCandidate(candidate);
		logger.info("returning from CandidateController.createCandidate candidate:{}", createdCandidate);
		return createdCandidate;
	}

	@PutMapping
	public Candidate updateCandidate(@RequestBody final Candidate candidate) {
		logger.info("calling CandidateController.updateCandidate candidate:{}", candidate);
		final Candidate updatedCandidate = candidateService.updateCandidate(candidate);
		logger.info("returning from CandidateController.updateCandidate candidate:{}", updatedCandidate);
		return updatedCandidate;
	}

	@DeleteMapping(value = "/{id}")
	public void deleteCandidate(@PathVariable final Long id) {
		logger.info("calling CandidateController.deleteCandidate id:{}", id);
		candidateService.deleteCandidate(id);
	}

	@GetMapping(value = "/{id}/recruite")
	public Candidate recruite(@PathVariable final Long id) {
		logger.info("calling CandidateController.recruite id:{}", id);
		final Candidate recruitedCandidate = candidateService.recruite(id);
		logger.info("calling CandidateController.recruite recruitedCandidate:{}", recruitedCandidate);
		return recruitedCandidate;
	}

	@GetMapping(value = "/{id}/reject")
	public Candidate reject(@PathVariable final Long id) {
		logger.info("calling CandidateController.reject id:{}", id);
		final Candidate rejectedCandidate = candidateService.reject(id);
		logger.info("calling CandidateController.reject rejectedCandidate:{}", rejectedCandidate);
		return rejectedCandidate;
	}
}
