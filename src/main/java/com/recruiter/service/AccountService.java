package com.recruiter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.recruiter.base.ServiceBase;

@Service
public class AccountService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping
	public String getAccountTotal() {
		logger.info("calling AccountService.getAccountTotal");
		logger.info("returning from AccountService.getAccountTotal");
		return "calling AccountService.getAccountTotal";
	}

	@PostMapping
	public String getAccountBreakdown() {
		logger.info("calling AccountService.getAccountBreakdown");
		logger.info("returning from AccountService.getAccountBreakdown");
		return "calling AccountService.getAccountBreakdown";
	}
}
