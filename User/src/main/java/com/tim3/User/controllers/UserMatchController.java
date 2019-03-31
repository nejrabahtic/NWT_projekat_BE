package com.tim3.User.controllers;

import com.tim3.User.models.UserMatch;
import com.tim3.User.services.UserMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("userMatch")
@CrossOrigin()
public class UserMatchController {

    @Autowired
    private UserMatchService userMatchService;

    @CrossOrigin
    @GetMapping()
    private ResponseEntity<List<UserMatch>> getAllUserMatch(){
        return new ResponseEntity<>(userMatchService.getAllUserMatches(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path="/{id}")
    private ResponseEntity<UserMatch> getUserMatchById(@PathVariable Integer id){
        return new ResponseEntity<>(userMatchService.getUserMatchById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(consumes = {"application/json"})
    private ResponseEntity<UserMatch> createUserMatch(@Valid @RequestBody UserMatch userMatch){
        return new ResponseEntity<>(userMatchService.createUserMatch(userMatch), HttpStatus.OK);
    }

    /*@CrossOrigin
    @GetMapping(path="/allUserMatches/{user_id}")
    private ResponseEntity<List<UserMatch>> getUserMatchesByUser(@PathVariable Integer user_id){
        return new ResponseEntity<>(userMatchService.getUserMatchesByUser(user_id), HttpStatus.OK);
    }*/

    @CrossOrigin
    @DeleteMapping(path="{id}")
    private ResponseEntity<Void> deleteRequestById(@PathVariable Integer id){
        userMatchService.deleteUserMatchById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
