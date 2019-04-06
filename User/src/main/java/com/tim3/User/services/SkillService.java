package com.tim3.User.services;

import com.tim3.User.models.Skill;
import com.tim3.User.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getAllSkills() {
        ArrayList<Skill> allSkills = new ArrayList<>();
        skillRepository.findAll().forEach(allSkills::add);
        return allSkills;
    }

    public Skill getSkillById(Integer id) {
        Optional<Skill> optionalSkill = skillRepository.findById(id);
        if(!optionalSkill.isPresent()){
            return null;
        }
        return optionalSkill.get();
    }

    public Skill createSkill(String skill_name) {
        Skill skill = new Skill(skill_name);
        return skillRepository.save(skill);
    }

    public void deleteSkillById(Integer id) {
        skillRepository.deleteById(id);
    }
}
