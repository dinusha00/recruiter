package com.recruiter.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JOB_TITLE", schema = "RECRUITER")
public class JobTitle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	protected JobTitle() {
	}

	public JobTitle(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
