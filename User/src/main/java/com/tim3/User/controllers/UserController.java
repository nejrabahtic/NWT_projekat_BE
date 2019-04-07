package com.tim3.User.controllers;

import com.tim3.User.models.Skill;
import com.tim3.User.models.User;
import com.tim3.User.services.SkillService;
import com.tim3.User.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SkillService skillService;

    @CrossOrigin
    @GetMapping()
    private ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path="/{id}")
    private ResponseEntity<User> getUserById(@PathVariable Integer id){
        User user = userService.getUserById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(consumes = {"application/json"})
    private ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user.getAuthId(), user.getUserInfo(), user.getUserName(),
                user.getUserEmail(), user.getUserPhoneNumber()), HttpStatus.CREATED);
    }
    @CrossOrigin
    @PostMapping(path = {"/{id}/skills"},consumes = {"application/json "})
    private ResponseEntity<User> addSkillsToUser(@PathVariable Integer id, @RequestBody List<Integer> skillsIds){
        List<Skill> skills =  skillService.getAllSkillsById(skillsIds);

        skills.forEach(skill -> {System.out.println(skill.getSkillName());});

        User user = userService.addSkillsToUserById(id, skills);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(path="{id}")
    private ResponseEntity<Void> deleteUserById(@PathVariable Integer id){
        User user = userService.getUserById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
