package com.tim3.Match.DTO;

import java.util.List;

public class CompanyDTO {

    private String companyName;
    private List<JobDTO> jobs;

    public List<JobDTO> getJobs() {
        return jobs;
    };

    public String getCompanyName() {
        return companyName;
    }
}
