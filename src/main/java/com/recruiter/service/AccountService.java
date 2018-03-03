package com.recruiter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recruiter.common.ServiceBase;

@RestController
@RequestMapping("/account")
public class AccountService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.GET)
	public String getAccountTotal() {
		logger.info("calling AccountService.getAccountTotal");
		return "calling AccountService.getAccountTotal";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String getAccountBreakdown() {
		logger.info("calling AccountService.getAccountBreakdown");
		return "calling AccountService.getAccountBreakdown";
	}
}
