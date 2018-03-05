package com.recruiter.domain.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruiter.domain.entity.Fee;

@Transactional
public interface FeeRepository extends JpaRepository<Fee, Long> {

	public List<Fee> findByJobTitleId(final int jobTitleId);
}
