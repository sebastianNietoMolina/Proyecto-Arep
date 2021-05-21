package com.prueba.apache.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class ConsumerKafka {

    @Autowired
    MainService mainService;

    @KafkaListener(topics = "proyecto",groupId = "group_id")
    public void consumeMessage(String message) throws IOException {
        String[] newMessage = message.split("-");
        mainService.sendMessageToPetition(newMessage[0], newMessage[1]);
    }
}
