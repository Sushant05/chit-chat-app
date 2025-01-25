package com.sushant.messageConsumer.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.sushant.messageConsumer.messageDataList.MessageDataList;
import com.sushant.messageConsumer.models.MessageData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class MessageController {

    @Autowired
    private MessageDataList messageDataList;

    @GetMapping("/getAll/messages")
    public List<MessageData> getAllMessages() {
        return messageDataList.getMessages();
    }
    
    
}
