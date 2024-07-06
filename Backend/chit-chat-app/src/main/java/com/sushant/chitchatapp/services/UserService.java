package com.sushant.chitchatapp.services;

import org.springframework.stereotype.Service;

import com.sushant.chitchatapp.models.JwtRequest;
import com.sushant.chitchatapp.models.JwtResponse;
import com.sushant.chitchatapp.models.User;

@Service
public interface UserService {
    public String userRegister(User user);
    public JwtResponse loginUser(JwtRequest request);
} 