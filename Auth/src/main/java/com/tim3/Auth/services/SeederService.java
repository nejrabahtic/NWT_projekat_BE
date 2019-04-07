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
        auths.add(new Auth("MaoZedong", bCryptPasswordEncoder.encode("communism")));
        auths.add(new Auth("BakeSDA", bCryptPasswordEncoder.encode("stopodsto")));
        auths.add(new Auth("DodoRS", bCryptPasswordEncoder.encode("korupcija1kroz1")));
        auths.add(new Auth("Heilhico", bCryptPasswordEncoder.encode("smrtnarodu")));
        auths.add(new Auth("Staljo", bCryptPasswordEncoder.encode("communism1kroz2")));
        auths.add(new Auth("Mussii", bCryptPasswordEncoder.encode("pastabolognese")));

        if(authRepository.findAll().isEmpty())
            authRepository.insert(auths);

    }
}
