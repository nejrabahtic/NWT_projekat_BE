package com.tim3.Log.models;

import com.tim3.Log.repositories.LogRepository;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Reciever {

    @Autowired
    private LogRepository logRepository;

    public void receiveMessage(HashMap<String, String> pairs){

        String table, action, description;

        table = pairs.getOrDefault("table", "none");
        action = pairs.getOrDefault("action", "none");
        description = pairs.getOrDefault( "description", "none");


        System.out.println("Recived Log Entry->  Table: " + table + ", Action: " + action + ", Description: " + description);

        Log log = new Log(table, action, description);

        logRepository.save(log);
    }

}
