package com.recruiter.domain.mapping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	protected JobTitle() {
	}

	public JobTitle(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
