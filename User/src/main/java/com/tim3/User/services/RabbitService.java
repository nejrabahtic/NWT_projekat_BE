package com.tim3.User.services;

import com.tim3.User.UserApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendUserLogData(String action, String description){
        HashMap<String, String> pairs = new HashMap<>();
        pairs.put("table", "User");
        pairs.put("action", action);
        pairs.put("description", description);

        rabbitTemplate.convertAndSend(UserApplication.LOG_EXCHANGE, "LOG.AUTH.LOGIN", pairs);
    }

}