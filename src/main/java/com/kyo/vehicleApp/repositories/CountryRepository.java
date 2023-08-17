package com.kyo.vehicleApp.repositories;

import com.kyo.vehicleApp.models.FuturePlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyo.vehicleApp.models.Country;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    List<Country> findByUsername(String username);
}
