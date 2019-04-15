package com.tim3.Company.services;

import com.tim3.Company.models.Job;
import com.tim3.Company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllSkills() {
        ArrayList<Job> allJobs = new ArrayList<>();
        jobRepository.findAll().forEach(allJobs:: add);
        return allJobs;
    }

    public Job getJobById(Integer id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if(!optionalJob.isPresent()){
            return null;
        }
        return optionalJob.get();
    }

    public Job createJob(String location, String jobinfo, String jobname, Boolean remote, Boolean partTime, String requirements) {
        Job job = new Job(location, jobinfo, jobname, remote, partTime, requirements);
        return jobRepository.save(job);
    }

    public List<Job> getAllJobsById(List<Integer> ids){
        ArrayList<Job> jobs = new ArrayList<>();
        jobRepository.findAllById(ids).forEach(jobs::add);
        return jobs;
    }

    public void deleteSkillById(Integer id) {
        jobRepository.deleteById(id);
    }
}
