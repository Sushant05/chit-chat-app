package com.sushant.chitchatapp.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sushant.chitchatapp.kafka.services.KafkaService;
import com.sushant.chitchatapp.models.MessageData;
import com.sushant.chitchatapp.services.MessageService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class MessageController {
    
    @Autowired
    private MessageService messageService;

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping("/send")
    public String addMessage(@RequestBody MessageData messageData) {
        String response = "";
        try{
            //String message = "{messgae:" + messageData.getMessage() + ", email:" + messageData.getEmail()+ "}";
            String message = mapper.writeValueAsString(messageData);
            kafkaService.uploadMessage(message);
            response = messageService.addMessage(messageData);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return response;
    }

    @GetMapping("/messages")
    public List<MessageData> getAllMessages() {
        List<MessageData> list = new ArrayList<>();
        try{
            list = messageService.fetchMessages();
            if(list.size()==0){
                System.out.println("list was empty initially");
                list = messageService.getMessages();
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        return list;
    }
    
}
