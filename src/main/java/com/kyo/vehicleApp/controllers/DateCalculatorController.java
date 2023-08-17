package com.kyo.vehicleApp.controllers;

import com.kyo.vehicleApp.models.User;
import com.kyo.vehicleApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyo.vehicleApp.models.DateCalculation;
import com.kyo.vehicleApp.services.DateCalculationService;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
public class DateCalculatorController {

    private final DateCalculationService dateCalculationService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public DateCalculatorController(DateCalculationService dateCalculationService) {
        this.dateCalculationService = dateCalculationService;
    }

    /*
        @GetMapping("/date-calculator")
        public String showDateCalculatorForm(Model model, Authentication authentication) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
            model.addAttribute("userdetail", userDetails);

            DateCalculation latestDateCalculation = dateCalculationService.getLatestDateCalculation();

            if (latestDateCalculation != null) {
                model.addAttribute("daysBetween", latestDateCalculation.getDaysBetween());
            }


            // Add the current date to the model as "startDate"
            model.addAttribute("startDate", LocalDate.now());

            return "date_calculator_form"; // Thymeleaf template name
        }

        @PostMapping("/date-calculator")
        public String calculateDaysBetweenDates(@RequestParam("endDate") LocalDate endDate, Model model) {
            LocalDate startDate = LocalDate.now();
            long daysBetween = dateCalculationService.calculateDaysBetweenDates(startDate, endDate);

            // Save the date calculation to the database, overwriting the existing record (if available)
            dateCalculationService.saveDateCalculation(startDate, endDate);

            model.addAttribute("daysBetween", daysBetween);
            model.addAttribute("endDate", endDate);
            // Add the current date to the model as "startDate" for the form display
            model.addAttribute("startDate", LocalDate.now());

            return "date_calculator_form"; // Thymeleaf template name
        }
        */
    @GetMapping("/date-calculator")
    public String showDateCalculatorForm(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetail", userDetails);

        DateCalculation latestDateCalculation = dateCalculationService.getLatestDateCalculation();

        // Pass the end date to the template if available
        if (latestDateCalculation != null) {
            model.addAttribute("endDate", latestDateCalculation.getEndDate());
        }

        // Create a new DateCalculation instance to ensure endDate attribute is always available
        model.addAttribute("dateCalculation", new DateCalculation());

        return "index"; // Thymeleaf template name
    }

    @PostMapping("/date-calculator")
    public String calculateDaysBetweenDates(@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime,
                                            Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetail", userDetails);

        LocalDateTime startDate = LocalDateTime.now();
        long daysBetween = dateCalculationService.calculateDaysBetweenDates(startDate, endDateTime);

        // Save the date calculation to the database, overwriting the existing record (if available)
        dateCalculationService.saveDateCalculation(startDate, endDateTime);

        // Pass the end date and days between to the template
        model.addAttribute("endDate", endDateTime);
        model.addAttribute("daysBetween", daysBetween);

        return "date_calculator_form"; // Thymeleaf template name
    }



}