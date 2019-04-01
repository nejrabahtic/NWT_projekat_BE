package com.tim3.Skill.services;

import com.tim3.Skill.models.Skill;
import com.tim3.Skill.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SeederService {

    @Autowired
    private SkillRepository skillRepository;

    public void seedSkills(){
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Forging"));
        skills.add(new Skill("PHP"));
        skills.add(new Skill("Networking"));
        skills.add(new Skill("Routing"));
        skills.add(new Skill("Java"));
        skills.add(new Skill("Oracle"));
        skills.add(new Skill("Nodejs"));
        skills.add(new Skill("MVC"));
        skills.add(new Skill("AWS"));
        skills.add(new Skill("Microservices"));
        skills.add(new Skill("Mentoring"));
        skills.add(new Skill("Analytics"));
        if(skillRepository.count() == 0 )
            skillRepository.saveAll( skills::iterator);
    }

}
