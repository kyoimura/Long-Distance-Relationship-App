package com.kyo.vehicleApp.repositories;

import com.kyo.vehicleApp.models.FuturePlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuturePlansRepository extends JpaRepository<FuturePlans, Integer> {
    List<FuturePlans> findByUsername(String username);

}
