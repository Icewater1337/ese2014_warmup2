package org.sample.controller.service;

import java.util.ArrayList;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TeamForm;
import org.sample.model.Team;
import org.sample.model.User;

public interface SampleService {

    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException;
    
    public TeamForm saveFrom( TeamForm teamForm) throws InvalidUserException;

	public ArrayList<Team> getTeams();
	
	public User getUser(Long userId);

	public Team getTeamObject(ArrayList<Team>teams, String string);
	

}
