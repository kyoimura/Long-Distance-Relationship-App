package com.kyo.vehicleApp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kyo.vehicleApp.models.FuturePlans;
import com.kyo.vehicleApp.models.Relationship;
import com.kyo.vehicleApp.models.User;
import com.kyo.vehicleApp.repositories.RelationshipRepository;
import com.kyo.vehicleApp.services.GoalTrackerService;
import com.kyo.vehicleApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kyo.vehicleApp.models.Country;
import com.kyo.vehicleApp.services.CountryService;

@Controller
public class CountryController {
	
	@Autowired
	private RelationshipRepository relationshipRepository;
	@Autowired
	private CountryService countryService;
	@Autowired
	private GoalTrackerService goalTrackerService;
	@Autowired
	private UserService userService;

	//Get All Countrys
	@GetMapping("countries")
	public String findAll(Model model, Authentication authentication){

		User userDetails = userService.findByUsername(authentication.getName());
		model.addAttribute("userdetail", userDetails);

		Relationship userRelationship = relationshipRepository.findByUser1IdOrUser2Id(userDetails.getId(), userDetails.getId());

		List<Country> currentComments = countryService.findAllByUsername(authentication.getName());
		List<Country> partnerComments = new ArrayList<>();

		String partnerUsername = null;
		if (userRelationship != null) {
			User partnerUser = (userDetails.getId() == userRelationship.getUser1().getId())
					? userRelationship.getUser2()
					: userRelationship.getUser1();

			partnerUsername = partnerUser.getUsername();
			partnerComments = countryService.findAllByUsername(partnerUser.getUsername());
		}
		model.addAttribute("partnerUsername", partnerUsername);
		model.addAttribute("partnerGoalName", goalTrackerService.getGoalName(partnerUsername));
		model.addAttribute("countries", currentComments);
		model.addAttribute("partnerComments", partnerComments);
		model.addAttribute("goalName", goalTrackerService.getGoalName(authentication.getName()));
		return "country";
	}
	@PostMapping("/save")
	public String add(@ModelAttribute Country country, Authentication authentication) {
		country.setUsername(authentication.getName());
		countryService.save(country);
		return "redirect:/countries";
	}

	@RequestMapping("countries/findById") 
	@ResponseBody
	public Country findById(Integer id)
	{
		return countryService.findById(id);
	}
	
	//Add Country
	@PostMapping(value="countries/addNew")
	public String addNew(Country country, Authentication authentication) {
		country.setUsername(authentication.getName());
		countryService.save(country);
		return "redirect:/countries";
	}

	@GetMapping("countries/addNew")
	public String addNew(Model model) {
		model.addAttribute("country", new Country());
		return "redirect:/countries";
	}

	@RequestMapping("/countries/update/{id}")
	public String update(@PathVariable("id") int id, Model model) {

		model.addAttribute("country", countryService.findById(id));
		//return "redirect:/countries";
		return "editCountry";
	}

	@PostMapping("/countries/update/{id}")
	public String updateCountry(@PathVariable int id, @ModelAttribute Country country) {
		//country.setId(id);
		countryService.save(country);
		return "redirect:/countries";
	}

	@RequestMapping("/countries/delete/{id}")
	public String delete(@PathVariable("id") int id)  {
		countryService.delete(id);
		return "redirect:/countries";
	}
}
