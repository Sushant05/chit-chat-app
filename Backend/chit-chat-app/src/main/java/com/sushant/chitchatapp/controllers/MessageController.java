package com.sushant.chitchatapp.controllers;

import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/send")
    public String addMessage(@RequestBody MessageData messageData) {
        String response = "";
        try{
            response = messageService.addMessage(messageData.getMessage(), messageData.getEmail());
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
            list = messageService.getMessages();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return list;
    }
    
}
