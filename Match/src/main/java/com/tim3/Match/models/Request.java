package com.tim3.Match.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;

@Entity
@Table
public class Request {

    @Id
    @GeneratedValue
    private Integer id;



    @Column(name = "accepted")
    private boolean accepted;


    public Request(){}

    @JsonProperty
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Integer id) {
        this.id = id;
    }


    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
