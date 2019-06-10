package com.tim3.User.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "skill")
public class Skill {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "skill_name")
    @Size(min = 1, max = 80, message = "Skill name must be between 1 and 80 characters")
    @NotNull(message = "Skill name must be provided.")
    private String skillName;

    public Skill(){}

    public Skill(String skill_name){
        this.skillName = skill_name;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getSkillName(){
        return skillName;
    }

    public void setSkillName(String skillName){
        this.skillName = skillName;
    }
}
