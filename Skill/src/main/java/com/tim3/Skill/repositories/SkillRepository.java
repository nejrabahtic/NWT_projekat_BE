package com.tim3.Skill.repositories;

import com.tim3.Skill.models.Skill;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SkillRepository extends CrudRepository<Skill, Integer> {
    Optional<Skill> findByName(String name);
}
