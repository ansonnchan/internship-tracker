package com.anson.internshiptracker.service;

import com.anson.internshiptracker.exception.BadRequestException;
import com.anson.internshiptracker.model.User;
import com.anson.internshiptracker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    // Register new user with hashed password
    public User registerUser(User user) {
        // check if email exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already registered");
        }
        
        // hash password before saving 
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    // find user by email 
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // validate login with BCrypt password comparison
    public boolean validateLogin(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        
        // Check if user exists and password matches
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }
}
