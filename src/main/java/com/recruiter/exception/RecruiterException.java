package com.recruiter.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RecruiterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public RecruiterException(final Exception e) {
		super("RecruiterException", e);
		logger.error("recruiter exception:{}", e.toString());
	}
}
