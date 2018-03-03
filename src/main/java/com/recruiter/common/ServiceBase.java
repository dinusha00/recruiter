package com.recruiter.common;

import org.springframework.beans.factory.annotation.Value;

public class ServiceBase {

	@Value("${jobtitle.notfound}")
	protected String msgJobTitleNotFound;

}
