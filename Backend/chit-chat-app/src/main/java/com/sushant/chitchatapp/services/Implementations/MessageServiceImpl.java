package com.sushant.chitchatapp.services.Implementations;

import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sushant.chitchatapp.models.MessageData;
import com.sushant.chitchatapp.models.User;
import com.sushant.chitchatapp.repositories.UserRepository;
import com.sushant.chitchatapp.services.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private UserRepository repository;


    @Override
    public String addMessage(String message, String email) {
        String response = "";
        try{
            User user = repository.findById(email).orElseThrow(() -> new UsernameNotFoundException("User not found for email: " + email));;
            LinkedHashMap<Long, String> messages = user.getMessages();
            Long currentTime = System.currentTimeMillis();
            messages.putIfAbsent(currentTime, message);
            user.setMessages(messages);
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
    public List<MessageData> getMessages() {
        List<MessageData> list = new ArrayList<>();
        try{
            List<User> users = repository.findAll();
            Map<Long, MessageData> messageDataMap = new LinkedHashMap<>();
            for(User user : users){
                LinkedHashMap<Long, String> messages = user.getMessages();
               // System.out.println(messages);
                for(Map.Entry<Long, String> entry : messages.entrySet()){
                    Long time = entry.getKey();
                    String message = entry.getValue();
                    String sender = user.getEmail();
                    MessageData messageData = new MessageData();
                    messageData.setEmail(sender);
                    messageData.setMessage(message);
                   // System.out.println(messageData);
                   // System.out.println("=============================");
                    messageDataMap.put(time, messageData);
                   // System.out.println(messageDataMap);
                }
              // System.out.println(messageDataMap);
            }

            for(Map.Entry<Long, MessageData> entry : messageDataMap.entrySet()){
                list.add(entry.getValue());
              //  System.out.println(list);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return list;
    }
    
    
}
