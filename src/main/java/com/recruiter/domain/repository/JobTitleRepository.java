package com.recruiter.domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruiter.domain.mapping.JobTitle;

@Transactional
public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {
}
