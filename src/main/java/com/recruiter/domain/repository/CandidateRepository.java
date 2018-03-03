package com.recruiter.domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruiter.domain.mapping.Candidate;

@Transactional
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
