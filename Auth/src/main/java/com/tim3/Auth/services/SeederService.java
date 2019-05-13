package com.tim3.Auth.services;

import com.tim3.Auth.models.Auth;
import com.tim3.Auth.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SeederService {
    @Autowired
    private AuthRepository authRepository;
    public void seedAuthTable(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ArrayList<Auth> auths = new ArrayList<>();

        // Users
        auths.add(new Auth(1,"MaoZedong", bCryptPasswordEncoder.encode("communism"), "user"));
        auths.add(new Auth(2,"BakeSDA", bCryptPasswordEncoder.encode("stopodsto"), "user"));
        auths.add(new Auth(3,"DodoRS", bCryptPasswordEncoder.encode("korupcija1kroz1"), "user"));
        auths.add(new Auth(4,"Heilhico", bCryptPasswordEncoder.encode("smrtnarodu"), "user"));
        auths.add(new Auth(5,"Staljo", bCryptPasswordEncoder.encode("communism1kroz2"), "user"));
        auths.add(new Auth(6,"Mussii", bCryptPasswordEncoder.encode("pastabolognese"), "user"));

        // Companies
        auths.add(new Auth(7,"Macrohard", bCryptPasswordEncoder.encode("acquireanddestroy"), "company"));
        auths.add(new Auth(8,"PearDoo", bCryptPasswordEncoder.encode("sameshit"), "company"));
        auths.add(new Auth(9,"IMPERIJA", bCryptPasswordEncoder.encode("jalabraca"), "company"));
        auths.add(new Auth(10,"BHtelekom", bCryptPasswordEncoder.encode("samorodbina"), "company"));

        if(authRepository.findAll().isEmpty())
            authRepository.insert(auths);

    }
}
