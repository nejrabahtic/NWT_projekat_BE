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
    public User getUserByAuthId(Integer authid){
        Optional<User> optionalUser = userRepository.findOneByAuthId(authid);

        return optionalUser.orElse(null);
    }
    public User setUserData(Integer authid, User user){
        Optional<User> optionalUser = userRepository.findOneByAuthId(authid);
        if(optionalUser.isPresent() == false)
            return null;
        User databaseUser = optionalUser.get();

        databaseUser.setUserEmail(user.getUserEmail());
        databaseUser.setUserInfo(user.getUserInfo());
        databaseUser.setUserName(user.getUserName());
        databaseUser.setUserPhoneNumber(user.getUserPhoneNumber());

        return userRepository.save(databaseUser);
    }

    public User createUser(Integer auth_id, String user_info, String user_name, String user_email, String user_phone_number) {
        User user = new User(auth_id, user_info, user_name, user_email, user_phone_number);
        rabbitService.sendUserLogData("CREATE", "User " + user.getId() + " created.");
        return userRepository.save(user);
    }

    public void createUser(Integer auth_id) {
        User user = new User(auth_id, null, null, null, null);
        rabbitService.sendUserLogData("CREATE", "User " + user.getId() + " created.");
        userRepository.save(user);
    }
    public User removeSkillFromUser(Integer authid, Skill skill){
        Optional<User> optionalUser = userRepository.findOneByAuthId(authid);
        if(optionalUser.isPresent() == false)
            return null;
        User user = optionalUser.get();
        user.removeSkill(skill);
        return userRepository.save(user);
    }
    public User addSkillsToUserByAuthId(Integer authid, List<Skill> skills){
        Optional<User> optionalUser = userRepository.findOneByAuthId(authid);
        if(optionalUser.isPresent() == false)
            return null;
        User user = optionalUser.get();
        user.addAllSkills(skills);
        return userRepository.save(user);

    }
    public void deleteUserById(Integer id) {
        rabbitService.sendUserLogData("DELETED", "User " + id.toString() + " deleted.");
        userRepository.deleteById(id);
    }
}
