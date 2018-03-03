package com.recruiter.base;

import org.springframework.beans.factory.annotation.Value;

public class ServiceBase {

	@Value("${jobtitle.notfound}")
	protected String msgJobTitleNotFound;

}
