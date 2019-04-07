package com.tim3.Skill.controllers;

import com.tim3.Skill.models.Skill;
import com.tim3.Skill.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping("skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public ResponseEntity<List<Skill>> getAll(){
        return new ResponseEntity<>(skillService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getById(@PathVariable Integer id){
        Skill skill = skillService.getById(id);
        if(skill == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody String name){
        if(!name.matches("[A-Za-z0-9]+"))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Skill skill = skillService.getByName(name);
        if(skill != null)
            return new ResponseEntity<>(skill, HttpStatus.CONFLICT);
        skill = skillService.create(name);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Skill> deleteSkill(@PathVariable Integer id){
        skillService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
