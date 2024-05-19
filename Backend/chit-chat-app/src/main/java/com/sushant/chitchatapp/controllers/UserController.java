package com.sushant.chitchatapp.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.sushant.chitchatapp.models.JwtRequest;
import com.sushant.chitchatapp.models.JwtResponse;
import com.sushant.chitchatapp.models.UserInfo;
import com.sushant.chitchatapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserInfo userInfo) {
        String response = "";
        try{
            response = userService.userRegister(userInfo);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return response;
    }
    
    @PostMapping("/login")
    public JwtResponse loginUser(@RequestBody JwtRequest request) {
        JwtResponse response = new JwtResponse();
        try{
            response = userService.loginUser(request);
        }
        catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return response;
    }
    
}
