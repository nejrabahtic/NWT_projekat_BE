package com.tim3.Skill.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;

import javax.persistence.*;


@Entity
@Table(name = "job_skill")
public class JobSkill{

    @Id
    private Integer id;

    @Column(name = "job_id")
    private Integer job_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Skill skill;

    public JobSkill(){}
    public JobSkill(Integer job_id, Skill skill){
        this.job_id = job_id;
        this.skill = skill;
    }

    public Integer getId() {
        return id;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public Skill getSkill() {
        return skill;
    }
}
