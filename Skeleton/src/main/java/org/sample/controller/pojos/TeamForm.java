package org.sample.controller.pojos;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class TeamForm {
	private String teamName;
	private Date date;
	private Long id;
	
	@NotNull
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public Date getDate () {
		return date;
	}
	
	
	public long getId () {
		return id;
		
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
