package com.tim3.Company.services;

import com.tim3.Company.models.Company;
import com.tim3.Company.models.Skill;
import com.tim3.Company.repositories.CompanyRepository;
import com.tim3.Company.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SeederService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SkillRepository skillRepository;

    public void seedCompanyTable(){
        ArrayList<Company> companies = new ArrayList<>();

        companies.add(new Company(7, "Great company all around, would recommend", "CompanyName", "mail@gmail.com", "+387 222 111"));
        companies.add(new Company(8, "Great company all around, would recommend", "MoneyAndFame", "mail@gmail.com", "+387 222 111"));
        companies.add(new Company(9, "Great company all around, would recommend", "JustMoney", "mail@gmail.com", "+387 222 111"));
        companies.add(new Company(10, "Great company all around, would recommend", "AnotherCompany", "mail@gmail.com", "+387 222 111"));

        if(companyRepository.count() == 0)
            companyRepository.saveAll(companies);
    }
    public void seedSkillTable(){
        ArrayList<Skill> skills = new ArrayList<>();

        skills.add(new Skill("Team Work"));
        skills.add(new Skill("Project Manager"));
        skills.add(new Skill("Software developer"));
        skills.add(new Skill("DB administrator"));

        if (skillRepository.count() == 0) {
            skillRepository.saveAll(skills);
        }
    }
}
