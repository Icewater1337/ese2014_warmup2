package org.sample.model;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.*;

public class Team {
	 @Id
	    @GeneratedValue
	    private String name;
	 	private Date date;
	    
	  	    
	    public String getTeamName() {
	        return name;
	    }

	    public void setTeamName(String name) {
	        this.name = name;
	    }
	    
	    public void setDate() {
	    	date = new Date();
	    }
	    
	    public Date getDate() {
	    	return date;
	    }

}
