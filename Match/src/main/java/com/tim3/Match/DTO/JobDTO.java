package com.tim3.Match.DTO;

import java.util.List;

public class JobDTO {
    private String jobName;
    private List<SkillDTO> jobSkills;

    public List<SkillDTO> getJobSkills() {
        return jobSkills;
    }

    public String getJobName() {
        return jobName;
    }
}
