package com.tim3.Log.services;

import java.util.*;
import com.tim3.Log.models.Log;
import com.tim3.Log.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public List<Log> getAll(){
        ArrayList<Log> logs = new ArrayList<>();
        logRepository.findAll().forEach(logs::add);
        return logs;
    }


}
