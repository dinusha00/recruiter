package com.recruiter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping (value = "/{id}/total")
	public String readAccountTotal() {
		logger.info("calling AccountController.readAccountTotal");
		accountService.readAccountTotal();
		return "calling AccountController.readAccountTotal";
	}

	@GetMapping (value = "/{id}/breakdown")
	public String readAccountBreakdown() {
		logger.info("calling AccountController.readAccountBreakdown");
		accountService.readAccountBreakdown();
		return "calling AccountController.readAccountBreakdown";
	}
}
