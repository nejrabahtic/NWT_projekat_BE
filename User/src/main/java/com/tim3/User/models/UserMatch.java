package com.tim3.User.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_match")
public class UserMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userMatch_sequence")
    @SequenceGenerator(name="userMatch_sequence", sequenceName="USERMATCH_SEQ")
    private Integer id;

    @Column(name = "job_id", nullable=false, length=10, unique=true)
    private Integer jobId;

    @Column(name = "accepted")
    private boolean accepted;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @JsonDeserialize
    private User user;

    public UserMatch (){}


    public UserMatch (Integer job_id, boolean accepted){
        this.jobId = job_id;
        this.accepted = accepted;
    }
    public UserMatch (Integer job_id, boolean accepted, User user){
        this.jobId = job_id;
        this.accepted = accepted;
        this.user = user;
    }

    @JsonGetter
    public Integer getId() {
        return this.id;
    }

    @JsonGetter
    public Integer getJobId() {
        return this.jobId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonGetter
    public boolean isAccepted() {
        return this.accepted;
    }

    @JsonGetter
    public User getUser() {
        return this.user;
    }
}
