package com.tim3.Auth.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

@Document
public class Auth {

    public interface LoginValidation {};
    public interface RegistrationValidation {};

    @Id
    private Integer id;

    @NotNull(message = "Username must be provided.", groups = {LoginValidation.class, RegistrationValidation.class})
    @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters", groups = {LoginValidation.class, RegistrationValidation.class})
    @Pattern(regexp = "^[\\p{IsLatin}\\p{IsCyrillic}][\\p{IsLatin}\\p{IsCyrillic}0-9]*$", message = "Username can only contain alphanumeric characters and must begin with a letter.")
    @Field("username")
    private String username;

    @NotNull(message = "Password must be provided.",groups = {LoginValidation.class, RegistrationValidation.class})
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters", groups = {LoginValidation.class, RegistrationValidation.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Field("password")
    private String password;

    @NotNull(message = "Account type must be specified.", groups = {RegistrationValidation.class})
    @Pattern(regexp = "^(company|user)$", groups = {RegistrationValidation.class})
    @Field("role")
    private String role;

    public Auth(){}

    public Auth(String username, String password){
        this.id =  new ObjectId().getCounter();
        this.username = username;
        this.password = password;
        this.role = "user";
    }

    public Auth(String username, String password, String role){
        this.id =  new ObjectId().getCounter();
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // For testing purposes only
    public Auth(Integer id, String username, String password, String role){
        this.id =  id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @JsonProperty
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNewId(){
        this.id = new ObjectId().getCounter();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
