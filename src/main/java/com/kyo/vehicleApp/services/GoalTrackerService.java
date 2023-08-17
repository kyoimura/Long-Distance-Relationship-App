package com.kyo.vehicleApp.services;

import com.kyo.vehicleApp.models.GoalTracker;
import com.kyo.vehicleApp.models.User;
import com.kyo.vehicleApp.repositories.GoalTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GoalTrackerService {

    private final GoalTrackerRepository goalTrackerRepository;

    private final UserService userService;

    @Autowired
    public GoalTrackerService(GoalTrackerRepository goalTrackerRepository, UserService userService) {
        this.goalTrackerRepository = goalTrackerRepository;
        this.userService = userService;
    }
/*
    public String getGoalName() {
        GoalTracker goalTracker = goalTrackerRepository.findById(1L).orElse(null);
        return (goalTracker != null) ? goalTracker.getGoalName() : "0";
    }
*/
    public String getGoalName(String username) {
        List<GoalTracker> userGoals = goalTrackerRepository.findByUsername(username);
        userGoals.sort(Comparator.comparing(GoalTracker::getId).reversed());

        if (!userGoals.isEmpty()) {
            GoalTracker mostRecentGoal = userGoals.get(0);
            return mostRecentGoal.getGoalName();
        } else {
            return "0";
        }
    }
    /*
    public void saveGoalName(String goalName) {
        GoalTracker goalTracker = new GoalTracker();
        goalTracker.setId(1L);
        goalTracker.setGoalName(goalName);
        goalTrackerRepository.save(goalTracker);
    }
*/
    public void saveGoalName(String goalName, String username) {
        GoalTracker goalTracker = new GoalTracker(goalName, username);
        //goalTracker.setGoalName(goalName);
        //goalTracker.setUsername(username);
        goalTrackerRepository.save(goalTracker);
    }
    /*
    public void deleteGoalName() {
        goalTrackerRepository.deleteById(1L);
    }*/
    public void deleteGoalName(String username) {
        goalTrackerRepository.deleteByUsername(username);
    }
}

