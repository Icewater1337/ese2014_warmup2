package org.sample.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import java.util.*;

@Entity
public class Team {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "teamName",unique=true)
	private String teamName;
	
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate() {
		date = new Date();
	}

}
