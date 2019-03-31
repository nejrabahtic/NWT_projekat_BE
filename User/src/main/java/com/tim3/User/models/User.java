package com.tim3.User.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name="user_sequence", sequenceName="USER_SEQ")
    private Integer id;

    @Column(name = "auth_id", nullable=true, length=10, unique=true)
    private Integer authId;

    @Column(name = "user_info", nullable=true, length=100)
    private String userInfo;

    @OneToMany( mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<UserMatch> userMatches;

    public User (){}

    public User (Integer auth_id, String user_info) {
        this.authId = auth_id;
        this.userInfo = user_info;
    }

    @JsonGetter
    public Integer getId() {
        return this.id;
    }

    @JsonGetter
    public Integer getAuthId() {
        return this.authId;
    }

    @JsonGetter
    public String getUserInfo() {
        return this.userInfo;
    }

    /*public void addUserMatch(UserMatch userMatch){
        if(userMatches == null)
            userMatches = new ArrayList<UserMatch>();
        userMatches.add(userMatch);
        userMatch.setUser(this);
    }*/
    /*public List<UserMatch> getUserMatches(){
        return userMatches;
    }*/
}
