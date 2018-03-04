package com.recruiter.domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruiter.domain.entity.Candidate;
import com.recruiter.domain.entity.JobTitle;

@Transactional
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	
	public JobTitle findByName(final String name);
}
