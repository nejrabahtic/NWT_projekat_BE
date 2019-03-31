package com.tim3.User.services;

import com.tim3.User.models.User;
import com.tim3.User.models.UserMatch;
import com.tim3.User.repositories.UserMatchRepository;
import com.tim3.User.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserMatchService {
    @Autowired
    private UserMatchRepository userMatchRepository;

    @Autowired
    private UserRepository userRepository;

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

    public UserMatch createUserMatch(UserMatch userMatch){
        Integer user_id = userMatch.getUser().getId();
        Optional<User> user = userRepository.findById(user_id);
        userMatch.setUser(user.orElse(null));
        return userMatchRepository.save(userMatch);
    }

    /*public List<UserMatch> getUserMatchesByUser(Integer user_id){

        ArrayList<UserMatch> userMatches = new ArrayList<>();
        for (UserMatch userMatch : userMatchRepository.findAll()) {
            if (userMatch.getUser().getId().equals(user_id)) {
                userMatches.add(userMatch);
            }
        }
        return userMatches;
    }*/

    public void deleteUserMatchById(Integer id){
        userMatchRepository.deleteById(id);
    }
}
