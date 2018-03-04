package com.recruiter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruiter.base.ServiceBase;
import com.recruiter.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountService accountService;

	@GetMapping
	public String getAccountTotal() {
		logger.info("calling AccountController.getAccountTotal");
		accountService.getAccountTotal();
		return "calling AccountController.getAccountTotal";
	}

	@PostMapping
	public String getAccountBreakdown() {
		logger.info("calling AccountController.getAccountBreakdown");
		accountService.getAccountBreakdown();
		return "calling AccountController.getAccountBreakdown";
	}
}
