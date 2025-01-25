package com.sushant.chitchatapp.services.Implementations;

import java.util.ArrayList;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sushant.chitchatapp.models.MessageData;
import com.sushant.chitchatapp.repositories.MessageDataRepository;
import com.sushant.chitchatapp.services.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageDataRepository repository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String addMessage(MessageData messageData) {
        String response = "";
        try{
            repository.save(messageData);
            response = "success";
        }
        catch(Exception e){
            System.out.println(e);
            response = "Failed";
        }
        
       return response;
    }

    @Override
    public List<MessageData> getMessages() {
        List<MessageData> list = new ArrayList<>();
        try{
            list = repository.findAll();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return list;
    }

    @Override
    public List<MessageData> fetchMessages() {
        String url = "http://localhost:8081/getAll/messages";
        // MessageData[] because RestTemplate doesn’t automatically map to a List
        MessageData[] response = restTemplate.getForObject(url, MessageData[].class);
        return List.of(response); // Convert array to list
    }
    
}
