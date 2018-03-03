package com.recruiter.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RECRUITMENT", schema = "RECRUITER")
public class Recruitment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Date date;

	protected Recruitment() {
	}

	public Recruitment(final Date date) {
		this.date = date;
	}

	public Date getDate() {
		return this.date;
	}

	public Long getId() {
		return id;
	}

	public void setDate(final Date date) {
		this.date = date;
	}
}
