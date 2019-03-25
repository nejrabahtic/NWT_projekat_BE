package com.tim3.Auth.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document
public class Auth {

    @Id
    private ObjectId id;

    @Field("username")
    private String username;
    @Field("password_hash")
    private String password_hash;
    @Field("name")
    private String name;
    @Field("email")
    private String email;
    @Field("phone_number")
    private String phone_number;
    @Field("location")
    private String location;

    public Auth(){}
    public Auth(String username, String password_hash, String name, String email, String phone_number, String location ){
        this.id = new ObjectId();
        this.username = username;
        this.password_hash = password_hash;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.location = location;
    }

    public ObjectId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getLocation() {
        return location;
    }
}
