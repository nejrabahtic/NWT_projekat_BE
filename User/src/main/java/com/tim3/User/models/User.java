package com.tim3.User.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name="user_sequence", sequenceName="USER_SEQ")
    private Integer id;

    @Column(name = "auth_id", nullable=false, length=10, unique=true)
    private Integer authId;

    @Column(name = "user_info", nullable=false, length=100)
    private String userInfo;

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
}
