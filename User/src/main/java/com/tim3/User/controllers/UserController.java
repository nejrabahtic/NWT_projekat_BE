package com.tim3.User.controllers;

import com.tim3.User.models.User;
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

    @CrossOrigin
    @GetMapping()
    private ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path="/{id}")
    private ResponseEntity<User> getUserById(@PathVariable Integer id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(consumes = {"application/json"})
    private ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user.getAuthId(), user.getUserInfo()), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(path="{id}")
    private ResponseEntity<Void> deleteUserById(@PathVariable Integer id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
