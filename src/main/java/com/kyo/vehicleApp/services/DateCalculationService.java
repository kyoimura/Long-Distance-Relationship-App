package com.kyo.vehicleApp.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.kyo.vehicleApp.models.DateCalculation;
import com.kyo.vehicleApp.repositories.DateCalculationRepository;

@Service
public class DateCalculationService {

    private final DateCalculationRepository dateCalculationRepository;

    @Autowired
    public DateCalculationService(DateCalculationRepository dateCalculationRepository) {
        this.dateCalculationRepository = dateCalculationRepository;
    }

    public long calculateDaysBetweenDates(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        long daysBetween = ChronoUnit.DAYS.between(startDateTime.toLocalDate(), endDateTime.toLocalDate());
        return daysBetween;
    }


    public DateCalculation getLatestDateCalculation() {
        List<DateCalculation> dateCalculations = dateCalculationRepository.findAll();
        if (!dateCalculations.isEmpty()) {
            return dateCalculations.get(0); // Fetch the first (and only) element in the list
        }
        return null;
    }

    public void saveDateCalculation(LocalDateTime startDate, LocalDateTime endDate) {
        long daysBetween = calculateDaysBetweenDates(startDate, endDate);

        List<DateCalculation> dateCalculations = dateCalculationRepository.findAll();

        DateCalculation dateCalculation;
        if (!dateCalculations.isEmpty()) {
            dateCalculation = dateCalculations.get(0); // Fetch the first (and only) element in the list
        } else {
            dateCalculation = new DateCalculation();
        }

        dateCalculation.setStartDate(startDate);
        dateCalculation.setEndDate(endDate);
        dateCalculation.setDaysBetween(daysBetween);

        dateCalculationRepository.save(dateCalculation);
    }
}