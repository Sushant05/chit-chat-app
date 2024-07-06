package com.sushant.messageConsumer.messageDataList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sushant.messageConsumer.models.MessageData;

import lombok.Getter;

@Component
@Getter
public class MessageDataList {
    
    public List<MessageData> messages = new ArrayList<>();
}
