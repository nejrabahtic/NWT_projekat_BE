package com.tim3.Skill.models;

import javax.persistence.*;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue
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
