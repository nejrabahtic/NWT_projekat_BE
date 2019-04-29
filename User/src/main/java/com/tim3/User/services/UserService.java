package com.tim3.User.services;

import com.netflix.discovery.converters.Auto;
import com.tim3.User.models.User;
import com.tim3.User.models.Skill;

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

    @Autowired
    private RabbitService rabbitService;

    public List<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<User>();
        userRepository.findAll().forEach(allUsers::add);
        return allUsers;
    }

    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.orElse(null);
    }

    public User createUser(Integer auth_id, String user_info, String user_name, String user_email, String user_phone_number) {
        User user = new User(auth_id, user_info, user_name, user_email, user_phone_number);
        rabbitService.sendUserLogData("CREATE", "User " + user.getId() + " created.");
        return userRepository.save(user);
    }


    public User addSkillsToUserById(Integer id, List<Skill> skills){
        User user = userRepository.findById(id).orElse(null);
        if(user == null)
            return null;
        user.addAllSkills(skills);
        return userRepository.save(user);

    }
    public void deleteUserById(Integer id) {
        rabbitService.sendUserLogData("DELETED", "User " + id.toString() + " deleted.");
        userRepository.deleteById(id);
    }
}
