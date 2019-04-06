package com.tim3.User.controllers;

import com.tim3.User.models.Skill;
import com.tim3.User.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @CrossOrigin
    @GetMapping()
    private ResponseEntity<List<Skill>> getAllSkills(){
        return new ResponseEntity<>(skillService.getAllSkills(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path="/{id}")
    private ResponseEntity<Skill> getSkillById(@PathVariable Integer id){
        Skill skill = skillService.getSkillById(id);
        if(skill == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(skillService.getSkillById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(consumes = {"application/json"})
    private ResponseEntity<Skill> createSkill(@RequestBody Skill skill){
        return new ResponseEntity<>(skillService.createSkill(skill.getSkillName()), HttpStatus.CREATED);
    }

    @CrossOrigin
    @DeleteMapping(path="{id}")
    private ResponseEntity<Void> deleteSkillById(@PathVariable Integer id){
        Skill skill = skillService.getSkillById(id);
        if(skill == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        skillService.deleteSkillById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
