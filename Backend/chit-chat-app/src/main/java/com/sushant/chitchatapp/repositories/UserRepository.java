package com.sushant.chitchatapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sushant.chitchatapp.models.User;

public interface UserRepository extends MongoRepository<User, String>{
    
}
