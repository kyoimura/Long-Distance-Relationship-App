package com.kyo.vehicleApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kyo.vehicleApp.models.DateCalculation;

@Repository
public interface DateCalculationRepository extends JpaRepository<DateCalculation, Long> {
}

