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
    public Skill getByName(String name){
        Optional<Skill> skill = skillRepository.findByName(name);
        return skill.orElse(null);
    }
    public Skill create(String name){
        Skill skill = new Skill(name);
        return skillRepository.save(skill);
    }
    public Skill getById(Integer id){
        Optional<Skill> skill = skillRepository.findById(id);
        return skill.orElse(null);
    }
    public void deleteById(Integer id){
        skillRepository.deleteById(id);
    }
}
