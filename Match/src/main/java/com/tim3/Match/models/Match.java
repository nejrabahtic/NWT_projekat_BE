package com.tim3.Match.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name="Matcher")
public class Match {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column(name = "user_id")
    private Integer userId;

    @NotNull
    @Column(name = "job_id")
    private Integer jobId;

    @NotNull
    @Length(max = 50)
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @Length(max = 50)
    @Column(name = "job_name")
    private String jobName;

    @NotNull
    @Length(max = 50)
    @Column(name = "company_name")
    private String companyName;

    public Match(){}

    public Match(Integer userId, Integer jobId, String userName, String jobName, String companyName){
        this.userId = userId;
        this.jobId = jobId;
        this.userName = userName;
        this.jobName = jobName;
        this.companyName = companyName;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;

    @JsonProperty
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
