package com.tim3.Skill.models;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    private Integer id;

    @Column(name="name", nullable=false)
    private String name;

    public Skill(){}

    public Skill(String name){
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
