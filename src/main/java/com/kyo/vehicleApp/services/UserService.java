package com.kyo.vehicleApp.services;

import org.springframework.stereotype.Service;

import com.kyo.vehicleApp.models.User;

@Service
public interface UserService {

	User save(User user);
	User findByUsername(String username);

	User findUserByVerificationCode(String verificationCode);
	String findCode(String username);
	User registerUser(String username, String password);
	boolean usernameExists(String username);

}
