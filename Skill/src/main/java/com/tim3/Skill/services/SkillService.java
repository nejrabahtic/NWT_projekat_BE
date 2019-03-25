package com.tim3.Skill.services;

import com.sun.tools.javac.util.List;
import com.tim3.Skill.models.Skill;
import com.tim3.Skill.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getAll(){
        return List.from(skillRepository.findAll());
    }

}
