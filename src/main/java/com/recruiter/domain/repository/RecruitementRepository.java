package com.recruiter.domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruiter.domain.Recruitment;

@Transactional
public interface RecruitementRepository extends JpaRepository<Recruitment, Long> {
}
