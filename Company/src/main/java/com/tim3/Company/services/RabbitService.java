package com.tim3.Company.services;

import com.tim3.Company.CompanyApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendCompanyLogData(String action, String description){

        HashMap<String, String> pairs = new HashMap<>();
        pairs.put("table", "Company");
        pairs.put("action", action);
        pairs.put("description", description);

        rabbitTemplate.convertAndSend(CompanyApplication.LOG_EXCHANGE, "LOG.AUTH.LOGIN", pairs);
    }

    public void sendJobLogData(String action, String description){

        HashMap<String, String> pairs = new HashMap<>();
        pairs.put("table", "Job");
        pairs.put("action", action);
        pairs.put("description", description);

        rabbitTemplate.convertAndSend(CompanyApplication.LOG_EXCHANGE, "LOG.AUTH.LOGIN", pairs);
    }
}
