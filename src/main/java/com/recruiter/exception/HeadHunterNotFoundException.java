package com.recruiter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HeadHunterNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HeadHunterNotFoundException(final String headHunterName) {
		super("could not find headhuner:" + headHunterName);
	}
}