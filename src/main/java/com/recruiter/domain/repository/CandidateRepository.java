package com.recruiter.domain.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruiter.domain.entity.Candidate;

@Transactional
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	public Candidate findByName(final String name);

	public List<Candidate> findByHeadhunterid(final Long headhhunterid);
}
