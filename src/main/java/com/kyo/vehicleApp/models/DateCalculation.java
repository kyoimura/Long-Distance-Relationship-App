package com.kyo.vehicleApp.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DateCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public DateCalculation() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public long getDaysBetween() {
        return daysBetween;
    }

    public void setDaysBetween(long daysBetween) {
        this.daysBetween = daysBetween;
    }

    private long daysBetween;

    // Constructors, getters, and setters
}