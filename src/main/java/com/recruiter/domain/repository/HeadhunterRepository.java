package com.recruiter.domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruiter.domain.entity.Headhunter;
import com.recruiter.domain.entity.JobTitle;

@Transactional
public interface HeadhunterRepository extends JpaRepository<Headhunter, Long> {
	
	public JobTitle findByName(final String name);
}
