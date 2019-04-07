package com.tim3.Auth.services;

import com.tim3.Auth.models.Auth;
import com.tim3.Auth.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    public Auth createAuth(Auth auth){
        return authRepository.insert(auth);
    }

    public Auth login(Auth auth) {
        Optional<Auth> existingAuth = authRepository.findByUsername(auth.getUsername());
        if(!existingAuth.isPresent())
            return null;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(bCryptPasswordEncoder.matches(auth.getPassword(), existingAuth.get().getPassword()))
            return existingAuth.get();
        return null;
    }

    public Auth register(Auth auth){
        auth.setNewId();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashed_password = bCryptPasswordEncoder.encode(auth.getPassword());
        auth.setPassword(hashed_password);
        return  authRepository.insert(auth);
    }
    public Auth getByUsername(String username){
        Optional<Auth> auth = authRepository.findByUsername(username);
        return auth.orElse(null);
    }
    public Auth getById(Integer id){
        Optional<Auth> auth = authRepository.findById(id);
        return auth.orElse(null);
    }
    public void deleteById(Integer id){
        authRepository.deleteById(id);
    }
    public List<Auth> getAll(){
        return authRepository.findAll();
    }
}
