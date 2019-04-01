package com.tim3.Skill.models;



import javax.persistence.*;

@Entity
@Table(name = "user_skill")
public class UserSkill {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", nullable = false)
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
