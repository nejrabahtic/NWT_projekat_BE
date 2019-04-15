package com.tim3.Company.repositories;

import com.tim3.Company.models.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Integer> {
}
