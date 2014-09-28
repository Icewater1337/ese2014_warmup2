package org.sample.controller.service;

import java.util.ArrayList;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TeamForm;
import org.sample.model.Address;
import org.sample.model.Team;
import org.sample.model.User;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.TeamDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	UserDao userDao;
	@Autowired
	AddressDao addDao;
	@Autowired
	TeamDao teamDao;

	@Transactional
	public SignupForm saveFrom(SignupForm signupForm)
			throws InvalidUserException {
		

		String firstName = signupForm.getFirstName();

		if (!StringUtils.isEmpty(firstName)
				&& "ESE".equalsIgnoreCase(firstName)) {
			throw new InvalidUserException("Sorry, ESE is not a valid name"); // throw
																				// exception
		}

		Address address = new Address();
		address.setStreet("TestStreet-foo");
		

		User user = new User();
		user.setFirstName(signupForm.getFirstName());
		user.setEmail(signupForm.getEmail());
		user.setLastName(signupForm.getLastName());
		user.setAddress(address);
		user.setTeam_Id(signupForm.getTeamId());
		user = userDao.save(user); // save object to DB

		// Iterable<Address> addresses = addDao.findAll(); // find all
		// Address anAddress = addDao.findOne((long)3); // find by ID

		signupForm.setId(user.getId());

		return signupForm;

	}

	@Transactional
	public TeamForm saveFrom(TeamForm teamForm) throws InvalidUserException {

		String teamName = teamForm.getTeamName();

		if (!StringUtils.isEmpty(teamName) && "ESE".equalsIgnoreCase(teamName)) {
			throw new InvalidUserException("Sorry, ESE is not a valid name"); // throw
																				// exception
		}

		Team team = new Team();
		team.setTeamName(teamForm.getTeamName());
		team.setId(teamForm.getId());
		team.setDate();

		team = teamDao.save(team); // save object to DB

		return teamForm;

	}

	public ArrayList<Team> getTeams() {
     
		Iterable<Team> teamsIter = teamDao.findAll();
		ArrayList<Team> teams = new ArrayList<Team>();
		for ( Team t : teamsIter) {
			teams.add(t);
		}
		
		return teams;
		
	}

	public User getUser(Long userId) {
		return userDao.findOne(userId);
	}

	/*
	*
	 * this method helps to find the Object, since I was not able to get the object itself out from the index.jsp
	 *
	public Team getTeamObject(ArrayList<Team> teamList, String id) {
		
		for ( int i = 0; i < teamList.size(); i++ ) {
			if( teamList.get(i).getTeamName().equals(id))
				//teamDao.delete((long) i+1);
				return teamList.get(i);
		}
		return null;
	}*/

}
