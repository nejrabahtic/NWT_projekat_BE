package com.tim3.Auth.services;

import com.tim3.Auth.models.Auth;
import com.tim3.Auth.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    public Auth createAuth(Auth auth){
        return authRepository.insert(auth);
    }
    public Auth login(String username, String password){
        return null;
    }
    public List<Auth> getAll(){
        return authRepository.findAll();
    }
}
