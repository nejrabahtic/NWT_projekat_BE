package com.tim3.Match.services;


import com.tim3.Match.models.Match;
import com.tim3.Match.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    public Match getById(Integer id){
        Optional<Match> match = matchRepository.findById(id);
        return match.orElse(null);
    }
    public List<Match> getAll(){
        ArrayList<Match> matches = new ArrayList<>();
        matchRepository.findAll().forEach(matches::add); ;
        return matches;
    }
    public List<Match> getByUserId(Integer id) {
        ArrayList<Match> matches = new ArrayList<>();
        matchRepository.findAllByUserId(id).forEach(matches::add);
        return matches;
    }
    public List<Match> getByJobId(Integer id){
        ArrayList<Match> matches = new ArrayList<>();
        matchRepository.findAllByJobId(id).forEach(matches::add);
        return matches;
    }
    public boolean existsById(Integer id){
        return matchRepository.existsById(id);
    }
    public Match create(Match match){
        return matchRepository.save(match);
    }
    public void deleteById(Integer id){
        matchRepository.deleteById(id);
    }

}
