package com.recruiter.domain;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {
}
