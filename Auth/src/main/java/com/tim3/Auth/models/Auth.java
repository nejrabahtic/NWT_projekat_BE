package com.tim3.Auth.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import javax.validation.constraints.Email;
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

    @NotNull(message = "Name must be provided", groups = {RegistrationValidation.class})
    @Pattern(regexp = "^[\\p{IsLatin}\\p{IsCyrillic}]+[ ][\\p{IsLatin}\\p{IsCyrillic}]+$", groups = {RegistrationValidation.class})
    @Field("name")
    private String name;

    @NotNull(message = "Email must be provided.",groups = {RegistrationValidation.class})
    @Field("email")
    @Email(message = "Email is in incorrect format.")
    private String email;

    @NotNull(message = "Phone number must be provided ",groups = {RegistrationValidation.class})
    @Field("phoneNumber")
    private String phoneNumber;

    @NotNull(message = "Location must be provided", groups = {RegistrationValidation.class})
    @Field("location")
    private String location;

    public Auth(){}
    public Auth(String username, String password, String name, String email, String phoneNumber, String location ){
        this.id = new ObjectId().getCounter();
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
