package com.kyo.vehicleApp.services;

import com.kyo.vehicleApp.models.User;
import com.kyo.vehicleApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    public UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
}

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String findCode(String  username) {
        User user = userRepository.findByUsername(username);
        return user.getVerificationCode();
    }
    @Override
    public User registerUser(String username, String password) {
        if (usernameExists(username)) {
            throw new RuntimeException("Username already exists");
        }

        String verificationCode = generateVerificationCode();

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); // Hash the password
        newUser.setVerificationCode(verificationCode);

        return userRepository.save(newUser);
    }
    @Override
    public User findUserByVerificationCode(String verificationCode) {
        return userRepository.findUserByVerificationCode(verificationCode);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    private String generateVerificationCode() {
        // Generate a random code or a unique identifier for invitation
        // You can use libraries like java.util.UUID or create your own logic
        // For simplicity, let's assume it's a random alphanumeric string of length 8
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

}
