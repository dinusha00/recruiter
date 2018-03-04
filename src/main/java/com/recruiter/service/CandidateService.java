package com.recruiter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruiter.base.ServiceBase;
import com.recruiter.domain.entity.Candidate;
import com.recruiter.domain.repository.CandidateRepository;

@Service
public class CandidateService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CandidateRepository candidateRepository;

	public List<Candidate> readCandidates() {
		logger.info("calling CandidateService.readCandidates");
		final List<Candidate> candidates = candidateRepository.findAll();
		logger.info("returning from CandidateService.readCandidates candidates:{}", candidates);
		return candidates;
	}

	public Candidate readCandidate(final Long id) {
		logger.info("calling CandidateService.readCandidate id:" + id);
		final Candidate candidate = candidateRepository.findOne(id);
		logger.info("returning from CandidateService.readCandidate candidate:{}", candidate);
		return candidate;
	}

	public Candidate createCandidate(final Candidate candidate) {
		logger.info("calling CandidateService.createCandidate candidate:{}", candidate);
		final Candidate createdCandidate = candidateRepository.save(candidate);
		logger.info("returning from CandidateService.createCandidate createdCandidate:{}", createdCandidate);
		return createdCandidate;
	}

	public Candidate updateCandidate(final Candidate candidate) {
		logger.info("calling CandidateService.updateCandidate candidate:{}", candidate);
		final Candidate updatedCandidate = candidateRepository.save(candidate);
		logger.info("returning CandidateService.updateCandidate updatedCandidate:{}", updatedCandidate);
		return updatedCandidate;
	}

	public void deleteCandidate(final Long id) {
		logger.info("calling CandidateService.deleteCandidate id:{}", id);
		candidateRepository.delete(id);
	}

	public Candidate recruite(final Long id) {
		logger.info("calling CandidateService.recruite id:{}", id);
		final Candidate candidate = candidateRepository.findOne(id);
		candidate.setRecruited(true);
		final Candidate createdCandidate = candidateRepository.save(candidate);
		logger.info("returning from CandidateService.recruite createdCandidate:{}", createdCandidate);
		return createdCandidate;
	}

	public Candidate reject(final Long id) {
		logger.info("calling CandidateService.reject id:{}", id);
		final Candidate candidate = candidateRepository.findOne(id);
		candidate.setRecruited(false);
		final Candidate rejectedCandidate = candidateRepository.save(candidate);
		logger.info("calling CandidateService.reject rejectedCandidate:{}", rejectedCandidate);
		return rejectedCandidate;
	}
}
