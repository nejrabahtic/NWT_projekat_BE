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
import java.util.Optional;

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
    @GetMapping(path="/authid/{authid}")
    private ResponseEntity<User> getUserByAuthId(@PathVariable Integer authid){
        User user = userService.getUserByAuthId(authid);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(path="/{authid}/change")
    private ResponseEntity<User> setUserData(@PathVariable Integer authid, @RequestBody User user){
        User savedUser = userService.setUserData(authid, user);
        if(savedUser == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

//    @CrossOrigin
//    @PostMapping(consumes = {"application/json"})
//    private ResponseEntity<User> createUser(@RequestBody User user){
//        return new ResponseEntity<>(userService.createUser(user.getAuthId(), user.getUserInfo(), user.getUserName(),
//                user.getUserEmail(), user.getUserPhoneNumber()), HttpStatus.CREATED);
//    }
    @CrossOrigin
    @PostMapping(consumes = {"application/json"})
    private ResponseEntity<Void> createUser(@RequestBody Integer authid){
        userService.createUser(authid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping(path = {"/{authid}/skills"},consumes = {"application/json "})
    private ResponseEntity<User> addSkillsToUser(@PathVariable Integer authid, @RequestBody List<Integer> skillsIds){
        List<Skill> skills =  skillService.getAllSkillsById(skillsIds);

        skills.forEach(skill -> {System.out.println(skill.getSkillName());});

        User user = userService.addSkillsToUserByAuthId(authid, skills);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(path = {"/{authid}/skill"},consumes = {"application/json "})
    private ResponseEntity<User> removeSkill(@PathVariable Integer authid, @RequestBody Skill skill){
        System.out.println("HIT");
        Skill databaseskill = skillService.getSkillById(skill.getId());
        if(databaseskill == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        User user = userService.removeSkillFromUser(authid, databaseskill);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user,HttpStatus.OK);
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
