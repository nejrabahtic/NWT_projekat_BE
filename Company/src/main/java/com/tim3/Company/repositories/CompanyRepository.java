package com.tim3.Company.repositories;

import com.tim3.Company.models.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    Optional<Company> findOneByAuthId(Integer AuthId);

}
