package com.anson.internshiptracker.service;

import com.anson.internshiptracker.model.User;
import com.anson.internshiptracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
   @Autowired
   private UserRepository userRepository;

   //register new user
   public User registerUser(User user) {
    //TODO: Add password hashing later 
    return userRepository.save(user);
   }

   //Find user by email
   public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
   }

   //validate login
   public boolean validateLogin(String email, String password) {
    Optional<User> user = userRepository.findByEmail(email);
    //TODO: add password hash comparison
    if (!user.isPresent() || !user.get().getPassword().equals(password)) {
        return false;
    }
    else {
        return true; 
    }
 
}

}
