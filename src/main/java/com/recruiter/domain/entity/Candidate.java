package com.recruiter.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Candidate.findByName", query = "SELECT c FROM Candidate c WHERE LOWER(c.name) = LOWER(?1)"),
	@NamedQuery(name = "Candidate.findByHeadhunter", query = "SELECT c FROM Candidate c WHERE c.headhunterid = ?1 and c.recruited = true"),
	})
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

	@Column(nullable = false)
	private Long jobtitleid;

	@Column(nullable = false)
	private Boolean recruited = false;

	@Column(name = "createddate", columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date createddate;

	protected Candidate() {
	}

	public Candidate(final Long id, final String name, final Long headhunterid, final Long jobtitleid, final Boolean recruited) {
		this.id = id;
		this.name = name;
		this.headhunterid = headhunterid;
		this.jobtitleid = jobtitleid;
		this.recruited = recruited;
	}

	public Candidate(final String name, final Long headhunterid, final Long jobtitleid, final Boolean recruited) {
		this.name = name;
		this.headhunterid = headhunterid;
		this.jobtitleid = jobtitleid;
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
		builder.append(", jobtitleid=");
		builder.append(jobtitleid);
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

	public Long getJobtitleid() {
		return jobtitleid;
	}
}
