package com.kyo.vehicleApp.models;

import jakarta.persistence.*;

@Entity
public class GoalTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "goalName")
    private String goalName;

    @Column(name = "username") // Make sure the column name matches your database schema
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public GoalTracker(){}

    public GoalTracker(String goalName, String username) {
        this.goalName = goalName;
        this.username = username;
    }
    public GoalTracker(Long id, String goalName) {
        this.id = id;
        this.username = username;
        this.goalName = goalName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }
    // Constructors, getters, setters, and other methods
}
