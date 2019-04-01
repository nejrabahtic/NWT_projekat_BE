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
        auths.add(new Auth("MaoZedong", bCryptPasswordEncoder.encode("communism"), "Mao Zedong", "maozedong@comumnism.ce", "0300/2323-1111", "Bejin, China"));
        auths.add(new Auth("BakeSDA", bCryptPasswordEncoder.encode("stopodsto"), "Bakir Izetbegovic", "bake@sda.ba", "061/111-222", "Sarajevo, Bosnia and Herzegovina"));
        auths.add(new Auth("DodoRS", bCryptPasswordEncoder.encode("korupcija1kroz1"), "Milorad Dodik", "dodo@snsd.rs", "061/111-222", "Istočno Sarajevo, Bosnia and Herzegovina"));
        auths.add(new Auth("Heilhico", bCryptPasswordEncoder.encode("smrtnarodu"), "Adolf Hilter", "hico@reich.de", "999/999-999", "Berlin, Germany"));
        auths.add(new Auth("Staljo", bCryptPasswordEncoder.encode("communism1kroz2"), "Joseph Stalin", "staljo@sssr.ru", "022/222-1212", "Moscow, Russia"));
        auths.add(new Auth("Mussii", bCryptPasswordEncoder.encode("pastabolognese"), "Benito Mussolini", "mussii@facism.it", "0322/333-992", "Rome, Italy"));

        if(authRepository.findAll().isEmpty())
            authRepository.insert(auths);

    }
}
