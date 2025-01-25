package com.sushant.messageConsumer.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageData {

    private String messageId = UUID.randomUUID().toString();
    private String message;
    private String email;
}
