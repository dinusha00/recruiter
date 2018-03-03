package com.recruiter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RecruitementException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecruitementException(final String detail) {
		super("exception occured while processing recruitement:" + detail);
	}
}