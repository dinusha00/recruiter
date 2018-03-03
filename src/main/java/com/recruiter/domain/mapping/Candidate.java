package com.recruiter.domain.mapping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "candidate")
public class Candidate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Long headhunterid;

	@Column(nullable = true)
	private Boolean recruited;

	protected Candidate() {
	}

	public Candidate(final String name, final Long headhunterid, final Boolean recruited) {
		this.name = name;
		this.headhunterid = headhunterid;
		this.recruited = recruited;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Candidate [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", headhunterid=");
		builder.append(headhunterid);
		builder.append("]");
		builder.append(", recruited=");
		builder.append(recruited);
		return builder.toString();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getHeadhunterid() {
		return headhunterid;
	}

	public Boolean getRecruited() {
		return recruited;
	}

	public void setRecruited(Boolean recruited) {
		this.recruited = recruited;
	}
}
