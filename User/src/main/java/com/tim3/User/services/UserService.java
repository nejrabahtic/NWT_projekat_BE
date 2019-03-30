package com.tim3.User.services;

import com.tim3.User.models.User;
import com.tim3.User.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        ArrayList<User> allUsers = new ArrayList<User>();
        userRepository.findAll().forEach(allUsers::add);
        return allUsers;
    }

    public User getUserById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            return null;
        }
        return optionalUser.get();
    }

    public User createUser(Integer auth_id, String user_info){
        User user = new User(auth_id, user_info);
        return userRepository.save(user);
    }

    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }
}
