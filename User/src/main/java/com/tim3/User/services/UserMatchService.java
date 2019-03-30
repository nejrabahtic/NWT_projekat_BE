package com.tim3.User.services;

import com.tim3.User.models.User;
import com.tim3.User.models.UserMatch;
import com.tim3.User.repositories.UserMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserMatchService {
    @Autowired
    private UserMatchRepository userMatchRepository;

    public List<UserMatch> getAllUserMatches(){
        ArrayList<UserMatch> allUserMatches = new ArrayList<UserMatch>();
        userMatchRepository.findAll().forEach(allUserMatches::add);
        return allUserMatches;
    }

    public UserMatch getUserMatchById(Integer id){
        Optional<UserMatch> optionalUserMatch = userMatchRepository.findById(id);
        if(!optionalUserMatch.isPresent()){
            return null;
        }
        return optionalUserMatch.get();
    }

    public UserMatch createUserMatch(Integer job_id, boolean accepted, User user){
        UserMatch userMatch = new UserMatch(job_id, accepted, user);
        return userMatchRepository.save(userMatch);
    }

    public List<UserMatch> getUserMatchesByUser(Integer user_id){

        ArrayList<UserMatch> userMatches = new ArrayList<>();
        for (UserMatch userMatch : userMatchRepository.findAll()) {
            if (userMatch.getUser().getId().equals(user_id)) {
                userMatches.add(userMatch);
            }
        }
        return userMatches;
    }

    public void deleteUserMatchById(Integer id){
        userMatchRepository.deleteById(id);
    }
}
