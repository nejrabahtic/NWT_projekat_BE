package com.tim3.User.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "auth_id", length=10, unique=true)
    @NotNull
    private Integer authId;

    @Column(name = "user_info")
    @Size(min = 20, max = 100, message = "User info must be between 20 and 100 characters")
    private String userinfo;

    @Column(name = "user_name")
    @Size(min = 6, max = 30, message = "Username must be between 6 and 30 characters")
//    @NotNull(message = "Username must be provided.")
    private String username;

    @Column(name = "user_email")
    @Size(min = 6, max = 30, message = "User email must be between 6 and 30 characters")
//    @NotNull(message = "Email must be provided.")
    private String useremail;

    @Column(name = "user_phone_number")
    @Size(min = 6, max = 20, message = "User phone number must be between 6 and 20 digits")
//    @NotNull(message = "Phone number must be provided.")
    private String userPhonenumber;

    @ManyToMany
    @JoinTable(
            name = "user_skills",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

    public User (){}

    public User (Integer auth_id, String user_info, String user_name, String user_email, String user_phone_number){
        this.authId = auth_id;
        this.userinfo = user_info;
        this.username = user_name;
        this.useremail = user_email;
        this.userPhonenumber = user_phone_number;

    }

    @JsonProperty
    public Integer getId(){
        return id;
    }

    @JsonIgnore
    public void setId(Integer id){
        this.id = id;
    }

    public Integer getAuthId(){
        return authId;
    }

    public void setAuthId(Integer authId){
        this.authId = authId;
    }

    public String getUserInfo(){
        return userinfo;
    }

    public void setUserInfo(String userInfo){
        this.userinfo = userInfo;
    }

    public String getUserName(){
        return username;
    }

    public void setUserName(String userName){
        this.username = userName;
    }

    public String getUserEmail(){
        return useremail;
    }

    public void setUserEmail(String userEmail){
        this.useremail = userEmail;
    }

    public String getUserPhoneNumber(){
        return userPhonenumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber){
        this.userPhonenumber = userPhoneNumber;
    }

    @JsonProperty
    public List<Skill> getSkills() {
        return skills;
    }

    @JsonIgnore
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void addSkill(Skill skill){
        skills.add(skill);
    }
    public void addAllSkills(List<Skill> new_skills){
        this.skills.addAll(new_skills);
    }

}
