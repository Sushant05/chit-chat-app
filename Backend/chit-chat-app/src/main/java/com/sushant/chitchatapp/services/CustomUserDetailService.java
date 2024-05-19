package com.sushant.chitchatapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sushant.chitchatapp.models.User;
import com.sushant.chitchatapp.repositories.UserRepository;

@Component
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepository repository; 

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User: "+username+ " not found"));
        return user;
    }
    
}
