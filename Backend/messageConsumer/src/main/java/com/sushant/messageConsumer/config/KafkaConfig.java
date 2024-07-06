package com.sushant.messageConsumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sushant.messageConsumer.messageDataList.MessageDataList;
import com.sushant.messageConsumer.models.MessageData;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MessageDataList messageDataList;

    @KafkaListener(topics = "message-topic", groupId = "group-1")
    public void listen(String message) {
        try{
            MessageData messageData = mapper.readValue(message, MessageData.class);
            messageDataList.getMessages().add(messageData);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
