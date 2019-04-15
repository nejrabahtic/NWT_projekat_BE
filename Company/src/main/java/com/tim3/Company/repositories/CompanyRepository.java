package com.tim3.Company.repositories;

import com.tim3.Company.models.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
}
