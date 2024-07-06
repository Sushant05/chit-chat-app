package com.sushant.chitchatapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sushant.chitchatapp.models.MessageData;

@Repository
public interface MessageDataRepository extends MongoRepository<MessageData, String>{

    
}
    

