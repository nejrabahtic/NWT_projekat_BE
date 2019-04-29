package com.tim3.Match.services;

import com.tim3.Match.MatchApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMatchLogData(String action, String description){

        HashMap<String, String> pairs = new HashMap<>();
        pairs.put("table", "Match");
        pairs.put("action", action);
        pairs.put("description", description);

        rabbitTemplate.convertAndSend(MatchApplication.LOG_EXCHANGE, "LOG.AUTH.LOGIN", pairs);
    }

    public void sendRequestLogData(String action, String description){

        HashMap<String, String> pairs = new HashMap<>();
        pairs.put("table", "Request");
        pairs.put("action", action);
        pairs.put("description", description);

        rabbitTemplate.convertAndSend(MatchApplication.LOG_EXCHANGE, "LOG.AUTH.LOGIN", pairs);
    }
}
