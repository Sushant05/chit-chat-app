package com.sushant.chitchatapp.kafka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void uploadMessage(String message){
        kafkaTemplate.send("message-topic", message);
        return;
    }
    
}
