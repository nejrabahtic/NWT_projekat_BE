package com.tim3.User.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(name = "user_info", length=100)
    @NotNull
    private String userInfo;

    @Column(name = "user_name", length=30)
    @NotNull
    private String userName;

    @Column(name = "user_email", length=30)
    @NotNull
    private String userEmail;

    @Column(name = "user_phone_number", length=15)
    @NotNull
    private String userPhoneNumber;

    @ManyToMany(mappedBy = "skills")
    private List<Skill> skills;

    public User (){}

    public User (Integer auth_id, String user_info, String user_name, String user_email, String user_phone_number){
        this.authId = auth_id;
        this.userInfo = user_info;
        this.userName = user_name;
        this.userEmail = user_email;
        this.userPhoneNumber = user_phone_number;

    }

    public Integer getId(){
        return id;
    }

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
        return userInfo;
    }

    public void setUserInfo(String userInfo){
        this.userInfo = userInfo;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public String getUserPhoneNumber(){
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber){
        this.userPhoneNumber = userPhoneNumber;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
