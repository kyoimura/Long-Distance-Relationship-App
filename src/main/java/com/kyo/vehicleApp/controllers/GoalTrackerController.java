package com.kyo.vehicleApp.controllers;

import com.kyo.vehicleApp.services.GoalTrackerService;
import com.kyo.vehicleApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;

@Controller
public class GoalTrackerController {

    @Autowired
    private GoalTrackerService goalTrackerService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;
    @GetMapping("/goal-tracker")
    public String showBudgetTracker(Model model, Authentication authentication) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        model.addAttribute("userdetail", userDetails);
        String goalName = goalTrackerService.getGoalName(authentication.getName());
        model.addAttribute("goalName", goalName);
        return "goals";
    }

    @PostMapping("/goal-tracker/set-goal-name")
    public String setGoalName(@RequestParam String goalName, Authentication authentication) {
        goalTrackerService.saveGoalName(goalName, authentication.getName());
        return "redirect:/goal-tracker";
    }

    @GetMapping("/goal-tracker/edit-goal-name")
    public String showEditGoalName(Model model, Authentication authentication) {
        String goalName = goalTrackerService.getGoalName(authentication.getName());
        model.addAttribute("goalName", goalName);
        return "editGoal";
    }

    @PostMapping("/goal-tracker/edit-goal-name")
    public String editGoalName(@RequestParam String goalName, Authentication authentication) {
        goalTrackerService.saveGoalName(goalName, authentication.getName());
        return "redirect:/goal-tracker";
    }

    @GetMapping("/goal-tracker/delete-goal-name")
    public String deleteGoalName(Authentication authentication) {
        goalTrackerService.deleteGoalName(authentication.getName());
        return "redirect:/goal-tracker";
    }
}

