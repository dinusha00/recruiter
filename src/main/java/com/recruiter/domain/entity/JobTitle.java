package com.recruiter.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "JobTitle.findByName", query = "SELECT j FROM JobTitle j WHERE LOWER(j.name) = LOWER(?1)")
@Table(name = "job_title")
public class JobTitle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	protected JobTitle() {
	}

	public JobTitle(final Long id, final String name) {
		this.id = id;
		this.name = name;
	}

	public JobTitle(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("JobTitle [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}
}
