package com.kyo.vehicleApp.services;

import java.util.List;
import java.util.Optional;

import com.kyo.vehicleApp.repositories.FuturePlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyo.vehicleApp.models.FuturePlans;


@Service
public class FuturePlansService {

    @Autowired
    private FuturePlansRepository futurePlansRepository;


    //Get All Plans
    public List<FuturePlans> findAll(){
        return futurePlansRepository.findAll();
    }
    public List<FuturePlans> findAllByUsername(String username) {
        return futurePlansRepository.findByUsername(username);
    }

    //Get Future Plan By Id
    public FuturePlans findById(int id) {

        return futurePlansRepository.findById(id).get();
    }

    //Delete Plan
    public void delete(int id) {
        futurePlansRepository.deleteById(id);
    }

    //Update Plan
    public void save( FuturePlans futurePlans) {

        futurePlansRepository.save(futurePlans);


    }

}
