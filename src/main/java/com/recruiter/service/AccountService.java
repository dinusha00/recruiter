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
	public String readAccountTotal() {
		logger.info("calling AccountService.readAccountTotal");
		logger.info("returning from AccountService.readAccountTotal");
		return "calling AccountService.readAccountTotal";
	}

	@PostMapping
	public String readAccountBreakdown() {
		logger.info("calling AccountService.readAccountBreakdown");
		throw new UnsupportedOperationException("this method is not implemented yet");
	}
}
