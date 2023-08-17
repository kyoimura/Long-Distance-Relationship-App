package com.kyo.vehicleApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyo.vehicleApp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	User findUserByVerificationCode(String verificationCode);
	String findVerificationCodeByUsername(String username);
}
