package com.tim3.User.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_sequence")
    @SequenceGenerator(name="request_sequence", sequenceName="REQUEST_SEQ")
    private Integer id;

    @Column(name = "accepted")
    private boolean accepted;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name = "userMatch_id")
    private UserMatch userMatch;

    public Request() {}

    public UserMatch getUserMatch() {
        return userMatch;
    }

    public void setUserMatch(UserMatch userMatch) {
        this.userMatch = userMatch;
    }

    public Request(boolean accepted, UserMatch userMatch){
        this.accepted = accepted;
        this.userMatch = userMatch;
    }

    @JsonGetter
    public Integer getId() {
        return this.id;
    }

    @JsonGetter
    public boolean isAccepted() {
        return this.accepted;
    }

}
