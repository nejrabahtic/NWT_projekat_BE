package com.tim3.Skill.controllers;

import com.sun.tools.javac.util.List;
import com.tim3.Skill.models.Skill;
import com.tim3.Skill.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public ResponseEntity<List<Skill>> getAll(){
        return new ResponseEntity<>(skillService.getAll(), HttpStatus.OK);
    }
}
