package com.kyo.vehicleApp.repositories;

import com.kyo.vehicleApp.models.GoalTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalTrackerRepository extends JpaRepository<GoalTracker, Long> {
    List<GoalTracker> findByUsername(String username);

    void deleteByUsername(String username);

}