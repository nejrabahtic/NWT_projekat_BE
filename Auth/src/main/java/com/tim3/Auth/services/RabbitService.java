package com.tim3.Auth.services;

import com.tim3.Auth.AuthApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RabbitService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendAuthLogData(String action, String description){

        HashMap<String, String> pairs = new HashMap<>();
        pairs.put("table", "Auth");
        pairs.put("action", action);
        pairs.put("description", description);

        rabbitTemplate.convertAndSend(AuthApplication.LOG_EXCHANGE, "LOG.AUTH.LOGIN", pairs);
    }

}
