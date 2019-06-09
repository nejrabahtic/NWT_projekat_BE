package com.tim3.Match.DTO;

import java.util.List;

public class MatchDTO {

    private Integer userId;
    private String userName;
    private List<SkillDTO> userSkills;

    private List<CompanyDTO> companyDTOS;

    public Integer getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public List<SkillDTO> getUserSkills() {
        return userSkills;
    }

    public List<CompanyDTO> getCompanyDTOS() {
        return companyDTOS;
    }
}
