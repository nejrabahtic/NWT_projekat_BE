package com.tim3.Company.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "location")
    @Size(min = 10, max = 80, message = "Location name must be between 20 and 30 characters")
    private String location;

    @Column(name = "job_info", length = 500)
    @Size(min = 20, max = 500, message = "Job info must be between 20 and 100 characters")
    private String jobinfo;

    @Column(name = "remote")
    @NotNull(message = "You must provide do you want remote or not.")
    private Boolean remote;

    @Column(name = "part_time")
    @NotNull(message = "You must provide do you want part time or not.")
    private Boolean partTime;

    @Column(name = "job_name", length = 80)
    @Size(min = 6, max = 80, message = "Job name must be between 6 and 30 characters")
    @NotNull(message = "Job name must be provided.")
    private String jobname;

    @Column(name = "requirements", length = 250)
    @Size(min = 10, max = 250, message = "Requirements must be between 10 and 30 characters")
    @NotNull(message = "Requirements must be provided.")
    private String requirements;

    @ManyToOne
    @JoinColumn
    private Company company;

    @ManyToMany
    @JoinTable(
            name = "job_skills",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

    public Job (){}

    public Job(String location, String jobinfo, String jobname, Boolean remote, Boolean partTime, String requirements){
        this.location = location;
        this.jobinfo = jobinfo;
        this.jobname = jobname;
        this.remote = remote;
        this.partTime = partTime;
        this. requirements = requirements;
    }

    @JsonProperty
    public Integer getId(){
        return id;
    }

    @JsonIgnore
    public void setId(Integer id){
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobinfo() {
        return jobinfo;
    }

    public void setJobinfo(String jobinfo) {
        this.jobinfo = jobinfo;
    }

    public Boolean getRemote() {
        return remote;
    }

    public void setRemote(Boolean remote) {
        this.remote = remote;
    }

    public Boolean getPartTime() {
        return partTime;
    }

    public void setPartTime(Boolean partTime) {
        this.partTime = partTime;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    @JsonProperty
    public List<Skill> getSkills() {
        return skills;
    }

    @JsonIgnore
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void addSkill(Skill skill){
        skills.add(skill);
    }
    public void addAllSkills(List<Skill> new_skills){
        this.skills.addAll(new_skills);
    }
}
