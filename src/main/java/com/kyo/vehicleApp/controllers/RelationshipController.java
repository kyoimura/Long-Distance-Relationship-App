package com.kyo.vehicleApp.controllers;
import com.kyo.vehicleApp.models.User;
import com.kyo.vehicleApp.models.UserPrincipal;
import com.kyo.vehicleApp.repositories.UserRepository;
import com.kyo.vehicleApp.services.RelationshipService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RelationshipController {
    private final UserDetailsService userDetailsService;
    private final RelationshipService relationshipService;

    private final UserRepository userRepository;

    public RelationshipController(UserDetailsService userDetailsService, RelationshipService relationshipService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.relationshipService = relationshipService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create-relationship")
    public String createRelationship(@RequestParam String partnerCode, Authentication authentication, Model model) {
        String currentUserUsername = authentication.getName();

        User currentUser = userRepository.findByUsername(currentUserUsername);
        User partnerUser = userRepository.findUserByVerificationCode(partnerCode);

        if (currentUser != null && partnerUser != null) {
            relationshipService.createRelationship(currentUser, partnerUser);
            model.addAttribute("successMessage", "Relationship created successfully.");
        } else {
            model.addAttribute("errorMessage", "Invalid partner verification code.");
        }

        return "confirmationPage"; // Return the name of your HTML confirmation page
    }
}

