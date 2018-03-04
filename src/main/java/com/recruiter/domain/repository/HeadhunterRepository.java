package com.recruiter.domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruiter.domain.entity.Headhunter;

@Transactional
public interface HeadhunterRepository extends JpaRepository<Headhunter, Long> {

	public Headhunter findByName(final String name);
}
