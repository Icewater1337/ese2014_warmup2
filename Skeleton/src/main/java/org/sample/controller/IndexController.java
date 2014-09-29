package org.sample.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidTeamException;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TeamForm;
import org.sample.controller.service.SampleService;
import org.sample.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import antlr.collections.List;

@Controller
public class IndexController {

	@Autowired
	SampleService sampleService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		
		ArrayList<Team> list = sampleService.getTeams();	
		ModelAndView model = new ModelAndView("index");
		model.addObject("signupForm", new SignupForm());
		model.addObject("teams", sampleService.getTeams());
		return model;

	}

	@RequestMapping(value = "/newteam", method = RequestMethod.GET)
	public ModelAndView indexTeam() {
		ModelAndView model = new ModelAndView("new-team");
		model.addObject("newTeamForm", new TeamForm());
		return model;
	}

	// @RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
	// @PathVariable Long userId

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView showProfileByUserId(
			@RequestParam(value = "userId", required = true) Long userId,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		ModelAndView model = new ModelAndView("profile");
		model.addObject("user", sampleService.getUser(userId));
		model.addObject("usersTeam", sampleService.getTeamQuery(sampleService.getUser(userId).getTeam_Id()));
		return model;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@Valid SignupForm signupForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		ModelAndView model;
		if (!result.hasErrors()) {
			try {
				//Team teamObject = sampleService.getTeamObject(sampleService.getTeams(), signupForm.getTeamName());
				//teamObject.setId(null);
				//signupForm.setTeamObj(sampleService.getTeamObject(sampleService.getTeams(), signupForm.getTeamName()));
				sampleService.saveFrom(signupForm);
				model = new ModelAndView("show");
			} catch (InvalidUserException e) {
				model = new ModelAndView("index");
				model.addObject("page_error", e.getMessage());
			}
		} else {
			model = new ModelAndView("index");
		}
		return model;
	}

	// @SuppressWarnings("deprecation")
	@RequestMapping(value = "/createTeam", method = RequestMethod.POST)
	public ModelAndView createTeam(@Valid TeamForm teamForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		ModelAndView model;
		if (!result.hasErrors()) {
			try {
				ArrayList<Team> teams = sampleService.getTeams();
				if( teams.size() < 1)
					teamForm.setId((long) 1);
				else {
				teamForm.setId((long) (sampleService.getTeams().size()+1));
				}
				sampleService.saveFrom(teamForm);
				model = new ModelAndView("show");
			} catch (InvalidTeamException e) {
				model = new ModelAndView("new-team");
				model.addObject("page_error", e.getMessage());
			}
		} else {
			model = new ModelAndView("index");
		}
		return model;
	}

	@RequestMapping(value = "/security-error", method = RequestMethod.GET)
	public String securityError(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("page_error",
				"You do have have permission to do that!");
		return "redirect:/";
	}

}

