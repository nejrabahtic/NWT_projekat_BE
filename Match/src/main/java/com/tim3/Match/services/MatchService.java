package com.tim3.Match.services;


import com.netflix.discovery.converters.Auto;
import com.tim3.Match.DTO.MatchDTO;
import com.tim3.Match.DTO.SkillDTO;
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

    @Autowired
    private RabbitService rabbitService;

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

        rabbitService.sendMatchLogData("CREATE", "Match " + match.getUserName() + " " + match.getCompanyName() + " found.");
        return matchRepository.save(match);
    }
    public void deleteById(Integer id){
        rabbitService.sendMatchLogData("DELETE", "Match " + id.toString() + " deleted.");
        matchRepository.deleteById(id);
    }

    private int computeScore(List<SkillDTO> userSkills, List<SkillDTO> jobSkills){
        int score = 0;
        for(int i = 0; i < userSkills.size(); i++){
            for(int j = 0; j < jobSkills.size(); j++){
                if(userSkills.get(i).getSkillName().equals(jobSkills.get(j).getSkillName())){
                    score++;
                }
            }
        }
        return score;
    }
    public Match computeMatch(MatchDTO matchDTO){

        List<Match> pastMatches = new ArrayList<>();
        matchRepository
                .findAll()
                .forEach(pastMatches::add);


        boolean alreadymatched = false;
        int bestscore = -1;
        int bestCompany = 0;
        int bestJob = 0;
        for(int i = 0; i < matchDTO.getCompanies().size(); i++){
            for(int j = 0; j < matchDTO.getCompanies().get(i).getJobs().size(); j++){
                alreadymatched = false;
                for(int k = 0; k < pastMatches.size(); k++){
                    if(matchDTO.getCompanies().get(i).getJobs().get(j).getJobId().equals(pastMatches.get(k).getJobId())){
                        alreadymatched = true;
                        break;
                    }
                }
                if(alreadymatched)
                    continue;

                int score = computeScore(matchDTO.getUserSkills(), matchDTO.getCompanies().get(i).getJobs().get(j).getJobSkills());
                if(score > bestscore){
                    bestscore = score;
                    bestCompany = i;
                    bestJob = j;
                }
            }
        }
        if(bestscore == -1)
            return null;
        Match match = new Match(
                            matchDTO.getUserId(),
                            matchDTO.getCompanies().get(bestCompany).getJobs().get(bestJob).getJobId(),
                            matchDTO.getUserName(),
                            matchDTO.getCompanies().get(bestCompany).getJobs().get(bestJob).getJobName(),
                            matchDTO.getCompanies().get(bestCompany).getCompanyName());
//        return matchRepository.save(match);
        return match;
    }

}
