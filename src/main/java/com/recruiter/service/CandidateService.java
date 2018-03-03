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
import com.recruiter.domain.mapping.Candidate;
import com.recruiter.domain.repository.CandidateRepository;

@RestController
@RequestMapping("/candidate")
public class CandidateService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CandidateRepository candidateRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Candidate> getCandidates() {
		logger.info("calling CandidateService.getCandidates");
		final List<Candidate> candidates = candidateRepository.findAll();
		logger.info("returning from CandidateService.getCandidates candidates.size():{}", candidates.size());
		return candidates;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Candidate getCandidate(@PathVariable final Long id) {
		logger.info("calling CandidateService.getCandidate id:" + id);
		final Candidate candidate = candidateRepository.findOne(id);
		logger.info("returning from CandidateService.getCandidate candidate:{}", candidate);
		return candidate;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Candidate addCandidate(@RequestBody final Candidate candidate) {
		logger.info("calling CandidateService.addCandidate candidate:{}", candidate);
		final Candidate createdCandidate = candidateRepository.save(candidate);
		return createdCandidate;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Candidate updateCandidate(@RequestBody final Candidate candidate) {
		logger.info("calling CandidateService.updateCandidate candidate:{}", candidate);
		final Candidate updatedCandidate = candidateRepository.save(candidate);
		return updatedCandidate;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteCandidate(@PathVariable final Long id) {
		logger.info("calling CandidateService.deleteCandidate id:{}", id);
		candidateRepository.delete(id);
	}
}
