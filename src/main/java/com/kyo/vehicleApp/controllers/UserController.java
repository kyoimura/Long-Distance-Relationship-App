package com.kyo.vehicleApp.controllers;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import com.kyo.vehicleApp.models.DateCalculation;
import com.kyo.vehicleApp.models.Relationship;
import com.kyo.vehicleApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kyo.vehicleApp.models.User;
import com.kyo.vehicleApp.services.UserService;
import com.kyo.vehicleApp.repositories.RelationshipRepository;
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private RelationshipRepository relationshipRepository;
	@Autowired
	private DateCalculationService dateCalculationService;

	@Autowired
	private GoalTrackerService goalTrackerService;

	private UserController(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public UserController(UserService userService, UserDetailsService userDetailsService, DateCalculationService dateCalculationService) {
		this.userService = userService;
		this.userDetailsService = userDetailsService;
		this.dateCalculationService = dateCalculationService;
	}

	@GetMapping("/index")
	public String index(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userdetail", userDetails);

		String verificationCode = ((CustomUserDetails) userDetails).getVerificationCode();
		model.addAttribute("verificationCode", verificationCode);

		User currentUser = userService.findByUsername(principal.getName());
		Relationship userRelationship = relationshipRepository.findByUser1IdOrUser2Id(currentUser.getId(), currentUser.getId());
		String partnerUsername = null;

		if (userRelationship != null) {
			User partnerUser = (currentUser.getId() == userRelationship.getUser1().getId())
					? userRelationship.getUser2() // If current user is user1
					: userRelationship.getUser1(); // If current user is user2

			partnerUsername = partnerUser.getUsername();
			model.addAttribute("inRelationship", true); // User is in a relationship
		} else {
			model.addAttribute("inRelationship", false); // User is not in a relationship
		}

		model.addAttribute("partnerGoalName", goalTrackerService.getGoalName(partnerUsername));
		//goal
		String goalName = goalTrackerService.getGoalName(principal.getName());
		model.addAttribute("goalName", goalName);


		// Calculate daysBetween using the DateCalculatorService or any other logic you have
		DateCalculation latestDateCalculation = dateCalculationService.getLatestDateCalculation();
		if (latestDateCalculation != null) {
			LocalDateTime startDate = LocalDateTime.now();
			LocalDateTime endDate = latestDateCalculation.getEndDate();
			long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
			model.addAttribute("daysBetween", daysBetween);
			model.addAttribute("dateCalculation", latestDateCalculation);
			model.addAttribute("endDate", endDate);
		}



		return "index";
	}
	@GetMapping("/login")
	public String login(Model model, User user) {
		model.addAttribute("user", user);
		return  "login";

	}
	/*
	@GetMapping("/register")
	public String register(Model model, User user) {
		model.addAttribute("user", user);
		return  "register";

	}

	@PostMapping("/register")
	public String registerSave(@ModelAttribute("user") User user, Model model) {

        User newUser = userService.findByUsername(user.getUsername());
        if (newUser != null) {
            model.addAttribute("userexist", newUser);
            return "register";

        }
        userService.save(user);
        return "redirect:/register?success";
    }*/
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User()); // Create a new User instance
		return "register";
	}

	@PostMapping("/register")
	public String registerSave(@ModelAttribute("user") User user, Model model) {
		try {
			userService.registerUser(user.getUsername(), user.getPassword());
		} catch (RuntimeException e) {
			model.addAttribute("userexist", true); // Set a flag for existing user
			return "register";
		}
		return "redirect:/register?success";
	}



}
