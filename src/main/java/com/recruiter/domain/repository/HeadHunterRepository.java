package com.recruiter.domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruiter.domain.HeadHunter;

@Transactional
public interface HeadHunterRepository extends JpaRepository<HeadHunter, Long> {
}