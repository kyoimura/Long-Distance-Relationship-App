package com.kyo.vehicleApp.models;

import jakarta.persistence.*;

@Entity
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    @ManyToOne
    private User user2;

    public Relationship() {
    }

    public Relationship(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }



    // Getters and setters
}

