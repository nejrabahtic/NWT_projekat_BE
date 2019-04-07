package com.tim3.Match.services;

import com.tim3.Match.models.Match;
import com.tim3.Match.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SeederService {
    @Autowired
    private MatchRepository matchRepository;
    public void seedMatchTable(){

        ArrayList<Match> matches = new ArrayList<>();
        matches.add(new Match());

        if(matchRepository.count() != 0)
            matchRepository.saveAll(matches);
    }
}
