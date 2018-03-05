package com.recruiter.domain.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruiter.domain.entity.Candidate;

@Transactional
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	public Candidate findByName(final String name);

	public List<Candidate> findByHeadhunterId(final Long headhhunterId);

	public List<Candidate> findByHeadhunterIdAndJobTitleId(final Long headhhunterId, final Long jobTitleId);

	public List<Candidate> findByHeadhunterIdAndJobTitleIdAndRecruited(final Long headhhunterId, final Long jobTitleId, final Boolean recruited);

}
