package com.tim3.Company.controllers;

import com.tim3.Company.models.Company;
import com.tim3.Company.models.Job;
import com.tim3.Company.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @CrossOrigin
    @GetMapping()
    private ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping(path="/authid/{authid}")
    private ResponseEntity<Company> getUserByAuthId(@PathVariable Integer authid){
        Company company = companyService.getCompanyByAuthId(authid);
        if(company == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping(path="/{authid}/change")
    private ResponseEntity<Company> setUserData(@PathVariable Integer authid, @RequestBody Company company){
        Company savedCompany = companyService.setCompanyData(authid, company);
        if(savedCompany == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(savedCompany, HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping(path="/{id}")
    private ResponseEntity<Company> getCompanyById(@PathVariable Integer id){
        Company company = companyService.getCompanyById(id);
        if(company == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(companyService.getCompanyById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(path = "/{authid}/addJob")
    private ResponseEntity<Company> addJobToCompany(@PathVariable Integer authid, @RequestBody Job job){
        Company company = companyService.addJobToCompany(authid, job);
        if(company == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(consumes = {"application/json"})
    private ResponseEntity<Void> createCompany(@RequestBody Integer authid){
        companyService.createCompany(authid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin
    @DeleteMapping(path="{id}")
    private ResponseEntity<Void> deleteCompanyById(@PathVariable Integer id){
        Company company = companyService.getCompanyById(id);
        if(company == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        companyService.deleteCompanyById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
