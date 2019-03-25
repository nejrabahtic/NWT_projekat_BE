package com.tim3.Skill.models;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "user_skill")
public class UserSkill {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Skill skill;

    public UserSkill(){}

    public UserSkill(Integer user_id, Skill skill){
        this.user_id = user_id;
        this.skill = skill;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Skill getSkill() {
        return skill;
    }
}
