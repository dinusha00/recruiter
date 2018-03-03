package com.recruiter.domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruiter.domain.mapping.Fee;

@Transactional
public interface FeeRepository extends JpaRepository<Fee, Long> {
}
