package com.kyo.vehicleApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyo.vehicleApp.models.Country;
import com.kyo.vehicleApp.repositories.CountryRepository;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;


	//Get All Countrys
	public List<Country> findAllByUsername(String username){
		return countryRepository.findByUsername(username);
	}	
	
	//Get Country By Id
	public Country findById(int id) {

		return countryRepository.findById(id).get();
	}	
	
	//Delete Country
	public void delete(int id) {
		countryRepository.deleteById(id);
	}
	
	//Update Country
	public void save( Country country) {

		countryRepository.save(country);


	}

}
