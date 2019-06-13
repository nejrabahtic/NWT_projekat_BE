package com.tim3.Match.controllers;

import com.netflix.discovery.converters.Auto;
import com.tim3.Match.DTO.MatchDTO;
import com.tim3.Match.models.Match;
import com.tim3.Match.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.OutputKeys;
import java.util.List;

@RestController
@RequestMapping("match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    public ResponseEntity<List<Match>> getAll(){
        return new ResponseEntity<>(matchService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Match> getById(@PathVariable Integer id){
        Match match = matchService.getById(id);
        if (match == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Match>> getByUserId(@PathVariable Integer id){
        return new ResponseEntity<>(matchService.getByUserId(id), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/job/{id}")
    public ResponseEntity<List<Match>> getByJobId(@PathVariable Integer id){
        return new ResponseEntity<>(matchService.getByJobId(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Match> create(@Valid @RequestBody Match match){
        Match createdMatch = matchService.create(match);
        return new ResponseEntity<>(createdMatch, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/make")
    public ResponseEntity<Match> matchUser(@RequestBody MatchDTO matchDTO){
        Match match = matchService.computeMatch(matchDTO);

        if(match == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/decide/{matchid}")
    public ResponseEntity<Match> matchUser(@PathVariable Integer matchid, @RequestBody Boolean accepted){
        Match match = matchService.createRequest(matchid, accepted);
        System.out.println(matchid);
        if(match == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        if(!matchService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        matchService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
