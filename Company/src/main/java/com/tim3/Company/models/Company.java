package com.tim3.Company.models;


import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "auth_id", length=10, unique=true)
    @NotNull
    private Integer authId;

    @Column(name = "company_info")
    @Size(min = 20, max = 100, message = "Company info must be between 20 and 100 characters")
    private String companyinfo;

    @Column(name = "company_name")
    @Size(min = 6, max = 30, message = "Company name must be between 6 and 30 characters")
//    @NotNull(message = "Company name must be provided.")
    private String companyname;

    @Column(name = "company_email")
    @Size(min = 6, max = 30, message = "Company email must be between 6 and 30 characters")
//    @NotNull(message = "Email must be provided.")
    private String companyemail;

    @Column(name = "company_phone_number")
    @Size(min = 6, max = 20, message = "Company phone number must be between 6 and 20 digits")
//    @NotNull(message = "Phone number must be provided.")
    private String companyphonenumber;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Job> jobs = new ArrayList<Job>();

    public Company(){}

    public Company(Integer authId, String companyinfo, String companyname, String companyemail, String companyphonenumber) {
        this.authId = authId;
        this.companyinfo = companyinfo;
        this.companyname = companyname;
        this.companyemail = companyemail;
        this.companyphonenumber = companyphonenumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getCompanyinfo() {
        return companyinfo;
    }

    public void setCompanyinfo(String companyinfo) {
        this.companyinfo = companyinfo;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail;
    }

    public String getCompanyphonenumber() {
        return companyphonenumber;
    }

    public void setCompanyphonenumber(String companyphonenumber) {
        this.companyphonenumber = companyphonenumber;
    }
    @JsonGetter
    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
    public void addJob(Job job){
        jobs.add(job);
    }


}
