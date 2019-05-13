package com.tim3.Company.services;

import com.tim3.Company.models.Company;
import com.tim3.Company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RabbitService rabbitService;

    public List<Company> getAllCompanies() {
        ArrayList<Company> allCompanies = new ArrayList<>();
        companyRepository.findAll().forEach(allCompanies:: add);
        return allCompanies;
    }

    public Company getCompanyById(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(!optionalCompany.isPresent()){
            return null;
        }
        return optionalCompany.get();
    }

    public Company createCompany(Integer authId, String companyinfo, String companyname, String companyemail, String companyphonenumber) {
        Company company = new Company(authId, companyinfo, companyname, companyemail, companyphonenumber);
        rabbitService.sendCompanyLogData("CREATE", "Company " + companyname + " created");
        return companyRepository.save(company);
    }
    public Company createCompany(Integer authId) {
        Company company = new Company(authId, null, null, null, null);
        rabbitService.sendCompanyLogData("CREATE", "Company " + authId.toString() + " created");
        return companyRepository.save(company);
    }
    public List<Company> getAllCompaniesById(List<Integer> ids){
        ArrayList<Company> companies = new ArrayList<>();
        companyRepository.findAllById(ids).forEach(companies::add);
        return companies;
    }

    public void deleteCompanyById(Integer id) {
        companyRepository.deleteById(id);
    }
}
