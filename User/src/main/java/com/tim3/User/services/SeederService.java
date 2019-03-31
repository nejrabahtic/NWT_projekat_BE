package com.tim3.User.services;

import com.tim3.User.models.Request;
import com.tim3.User.models.User;
import com.tim3.User.models.UserMatch;
import com.tim3.User.repositories.RequestRepository;
import com.tim3.User.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SeederService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    public void seedUserTable(){
        ArrayList<User> users = new ArrayList<>();

        users.add(new User(5, "userinfo5"));
        users.add(new User(6, "userinfo6"));
        users.add(new User(7, "userinfo7"));
        users.add(new User(8, "userinfo8"));

        if (userRepository.count() == 0) {
            userRepository.saveAll(users);
        }
    }

    /*public void seedRequestTable(){
        ArrayList<Request> requests = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        requests.add(new Request(false, new UserMatch(2, false, new User(3, "u3"))));
        requests.add(new Request(false, new UserMatch(4, false, users.get(2))));

        if (requestRepository.count() == 0) {
            requestRepository.saveAll(requests);
        }
    }*/

}
