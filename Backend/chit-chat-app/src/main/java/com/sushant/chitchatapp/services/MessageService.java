package com.sushant.chitchatapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sushant.chitchatapp.models.MessageData;

@Service
public interface MessageService {
    
    public String addMessage(MessageData messageData);
    public List<MessageData> getMessages();
    public List<MessageData> fetchMessages();

}
