package com.tim3.User.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_sequence")
    @SequenceGenerator(name="request_sequence", sequenceName="REQUEST_SEQ")
    private Integer id;

    @Column(name = "accepted")
    private boolean accepted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_USERMATCH")
    private UserMatch userMatch;

    public Request() {}

    public Request(boolean accepted, UserMatch userMatch) {
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

    @JsonGetter
    public UserMatch getUserMatch() {
        return this.userMatch;
    }
}
