package com.recruiter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruiter.base.ServiceBase;
import com.recruiter.domain.entity.Candidate;
import com.recruiter.domain.repository.CandidateRepository;
import com.recruiter.domain.repository.HeadhunterRepository;
import com.recruiter.domain.repository.JobTitleRepository;

@Service
public class CandidateService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private JobTitleRepository jobTitleRepository;

	@Autowired
	private HeadhunterRepository headhunterRepository;

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
		commonValidation(candidate);
		final Candidate createdCandidate = candidateRepository.save(candidate);
		logger.info("returning from CandidateService.createCandidate createdCandidate:{}", createdCandidate);
		return createdCandidate;
	}

	private void commonValidation(final Candidate candidate) {
		if (candidate == null || null == candidate.getName() || candidate.getName().isEmpty()) {
			throw new IllegalArgumentException(msgCandidateCannotBeEmpty);
		} else if (candidateRepository.findByName(candidate.getName()) != null) {
			throw new IllegalArgumentException(msgCandidateAlreadyExists);
		} else if (candidate.getHeadhunterId() == null) {
			throw new IllegalArgumentException(msgHeadhunterCannotBeEmpty);
		} else if (headhunterRepository.exists(candidate.getHeadhunterId()) == false) {
			throw new IllegalArgumentException(msgHeadhunterDoesnotExists);
		} else if (candidate.getJobTitleId() == null) {
			throw new IllegalArgumentException(msgJobTitleCannotBeEmpty);
		} else if (jobTitleRepository.exists(candidate.getJobTitleId()) == false) {
			throw new IllegalArgumentException(msgJobTitleDoesnotExists);
		}
	}

	public Candidate updateCandidate(Candidate candidate) {
		logger.info("calling CandidateService.updateCandidate candidate:{}", candidate);
		commonValidation(candidate);
		if (candidateRepository.findOne(candidate.getId()) == null) {
			throw new IllegalArgumentException(msgCandidateDoesnotExists);
		}
		candidate.setCreatedDate(candidateRepository.findOne(candidate.getId()).getCreatedDate());
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

	public List<Candidate> readHeadhunterCandidates(final Long headhunterId) {
		logger.info("calling CandidateService.readHeadhunterCandidates");
		final List<Candidate> headhunterCandidates = candidateRepository.findByHeadhunterIdAndRecruited(headhunterId, true);
		logger.info("returning from CandidateService.readHeadhunterCandidates headhunterCandidates:{}", headhunterCandidates);
		return headhunterCandidates;
	}
}
