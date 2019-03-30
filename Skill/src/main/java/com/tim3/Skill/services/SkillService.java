package com.tim3.Skill.services;

import java.util.*;
import com.tim3.Skill.models.Skill;
import com.tim3.Skill.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getAll(){
        ArrayList<Skill> skills = new ArrayList<>();
        skillRepository.findAll().forEach(skills::add);
        return skills;
    }

}
