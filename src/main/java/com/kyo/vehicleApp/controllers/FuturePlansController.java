package com.kyo.vehicleApp.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kyo.vehicleApp.models.Relationship;
import com.kyo.vehicleApp.models.User;
import com.kyo.vehicleApp.repositories.RelationshipRepository;
import com.kyo.vehicleApp.repositories.UserRepository;
import com.kyo.vehicleApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import com.kyo.vehicleApp.models.FuturePlans;
import com.kyo.vehicleApp.services.FuturePlansService;
import com.kyo.vehicleApp.repositories.RelationshipRepository;
@Controller
public class FuturePlansController {

    @Autowired
    private UserService userService;

    @Autowired
    private FuturePlansService futurePlansService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RelationshipRepository relationshipRepository;

    @Autowired
    private UserRepository userRepository;


    //Get All Plans
    @GetMapping("/futurePlans")
    public String findAll(Model model, Authentication authentication){
        //UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        User userDetails = userService.findByUsername(authentication.getName());
        model.addAttribute("userdetail", userDetails);

        //User currentUser = userService.findByUsername(authentication.getName());
        //model.addAttribute("user", currentUser);
        //model.addAttribute("futurePlans", futurePlansService.findAll());

        Relationship userRelationship = relationshipRepository.findByUser1IdOrUser2Id(userDetails.getId(), userDetails.getId());

        List<FuturePlans> userFuturePlans = futurePlansService.findAllByUsername(authentication.getName());
        List<FuturePlans> partnerFuturePlans = new ArrayList<>();
        String partnerUsername = null;

        if (userRelationship != null) {
            User partnerUser = (userDetails.getId() == userRelationship.getUser1().getId())
                    ? userRelationship.getUser2()
                    : userRelationship.getUser1();

            partnerUsername = partnerUser.getUsername();
            partnerFuturePlans = futurePlansService.findAllByUsername(partnerUser.getUsername());
        }
        model.addAttribute("partnerUsername", partnerUsername);
        model.addAttribute("futurePlans", userFuturePlans);
        model.addAttribute("partnerFuturePlans", partnerFuturePlans);

       // model.addAttribute("futurePlans", futurePlansService.findAllByUsername(authentication.getName()));

        return "futurePlans";
    }

    @PostMapping("/saveFuturePlans")
    public String add(@ModelAttribute FuturePlans futurePlans, Authentication authentication) {
        futurePlans.setUsername(authentication.getName());
        futurePlansService.save(futurePlans);
        return "redirect:/futurePlans";
    }

    @RequestMapping("futurePlans/findById")
    @ResponseBody
    public FuturePlans findById(Integer id)
    {
        return futurePlansService.findById(id);
    }

    //Add Country
    @PostMapping(value="futurePlans/addNew")
    public String addNew(FuturePlans futurePlans, Authentication authentication) {
        futurePlans.setUsername(authentication.getName());
        futurePlansService.save(futurePlans);
        return "redirect:/futurePlans";
    }

    @GetMapping("futurePlans/addNew")
    public String addNew(Model model) {
        model.addAttribute("futurePlan", new FuturePlans());
        return "redirect:/futurePlans";
    }

    @RequestMapping("/futurePlans/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {

        model.addAttribute("futurePlan", futurePlansService.findById(id));
        return "editFuturePlans";
    }

    @PostMapping("/futurePlans/update/{id}")
    public String updateFuturePlans(@PathVariable int id, @ModelAttribute FuturePlans futurePlans) {
        futurePlansService.save(futurePlans);
        return "redirect:/futurePlans";
    }

    @RequestMapping("/futurePlans/delete/{id}")
    public String delete(@PathVariable("id") int id)  {
        futurePlansService.delete(id);
        return "redirect:/futurePlans";
    }
}

