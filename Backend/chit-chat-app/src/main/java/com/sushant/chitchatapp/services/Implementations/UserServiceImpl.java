package com.sushant.chitchatapp.services.Implementations;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sushant.chitchatapp.models.JwtRequest;
import com.sushant.chitchatapp.models.JwtResponse;
import com.sushant.chitchatapp.models.User;
import com.sushant.chitchatapp.models.UserInfo;
import com.sushant.chitchatapp.repositories.UserRepository;
import com.sushant.chitchatapp.security.JwtHelper;
import com.sushant.chitchatapp.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String userRegister(UserInfo userInfo) {
        String response = "";
        try{
            User user = new User();
            user.setEmail(userInfo.getEmail());
            user.setName(userInfo.getName());
            user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            user.setMessages(new LinkedHashMap<Long, String>());
            repository.save(user);
            response = "success";
        }
        catch(Exception e){
            System.out.println(e);
            response = "Failed";
        }

        return response;
    }

    @Override
    public JwtResponse loginUser(JwtRequest request) {
        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = new JwtResponse();
        response.setJwtToken(token);
        response.setUsername(request.getEmail());
        return response;
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
    
}
