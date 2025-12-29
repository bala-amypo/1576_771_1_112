package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public User registerUser(User user) {
        try {
            if (user == null) {
                throw new BadRequestException("User cannot be null");
            }
            
            if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
                throw new BadRequestException("Email cannot be null or empty");
            }
            
            if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                throw new BadRequestException("Password cannot be null or empty");
            }
            
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new BadRequestException("Email already exists");
            }
            
            String rawPassword = user.getPassword();
            user.setPassword(passwordEncoder.encode(rawPassword));
            
            return userRepository.save(user);
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user: " + e.getMessage(), e);
        }
    }
    
    @Override
    public User findByEmail(String email) {
        try {
            if (email == null || email.trim().isEmpty()) {
                throw new ResourceNotFoundException("Email cannot be null or empty");
            }
            
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error finding user: " + e.getMessage(), e);
        }
    }
}