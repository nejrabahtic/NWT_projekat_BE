package com.tim3.Match.DTO;

import java.util.List;

public class JobDTO {
    private Integer jobId;
    private String jobName;
    private List<SkillDTO> jobSkills;

    public List<SkillDTO> getJobSkills() {
        return jobSkills;
    }

    public Integer getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }
}
