package com.tim3.Auth.services;

import com.tim3.Auth.AuthApplication;
import com.tim3.Auth.models.Auth;
import com.tim3.Auth.repositories.AuthRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private RabbitService rabbitService;

    private String secret;
    private Long expiration;

    private RestTemplate restTemplate;
    public AuthService(){
        this.secret = "verysecretkey";
        this.expiration = 18000L;
        this.restTemplate = new RestTemplate();
    }


    public List<Auth> getAll(){
        return authRepository.findAll();
    }

    public Auth getByUsername(String username){
        Optional<Auth> auth = authRepository.findByUsername(username);
        return auth.orElse(null);
    }
    public Auth getById(Integer id){
        Optional<Auth> auth = authRepository.findById(id);
        return auth.orElse(null);
    }

    public Auth createAuth(Auth auth){
        return authRepository.insert(auth);
    }

    public Auth login(Auth auth) {
        Optional<Auth> existingAuth = authRepository.findByUsername(auth.getUsername());
        if(!existingAuth.isPresent())
            return null;

        if(!existingAuth.get().getRole().equals(auth.getRole()))
            return null;

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(!bCryptPasswordEncoder.matches(auth.getPassword(), existingAuth.get().getPassword()))
            return null;

        rabbitService.sendAuthLogData("LOGIN", "USER " + existingAuth.get().getUsername() + " loged in");
        return existingAuth.get();
    }

    public Auth register(Auth auth){
        auth.setNewId();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashed_password = bCryptPasswordEncoder.encode(auth.getPassword());
        auth.setPassword(hashed_password);

        rabbitService.sendAuthLogData("REGISTRATION", "USER " + auth.getUsername() + " registered" );
        return  authRepository.insert(auth);
    }

    public void deleteById(Integer id){
        authRepository.deleteById(id);
    }

    public String generateToken(String id, String username, String role) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setId(id)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .claim("role",role)
                .compact();
    }
    public String validToken(String token){
        String id;
        try{
            id = Jwts.parser()
                     .setSigningKey(secret)
                     .parseClaimsJws(token)
                     .getBody()
                     .getId();

        } catch (JwtException e){
            e.printStackTrace();
            return null;
        }
        return id;
    }
    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 10000);
    }

    public HttpStatus createUser(Integer authid){

        HttpEntity<Integer> httpEntity = new HttpEntity<>(authid);
        ResponseEntity<Void> responseEntity = restTemplate.exchange("http://localhost:8082/users", HttpMethod.POST, httpEntity, Void.class);
        return responseEntity.getStatusCode();
    }
    public HttpStatus createCompany(Integer authid){

        HttpEntity<Integer> httpEntity = new HttpEntity<>(authid);
        ResponseEntity<Void> responseEntity = restTemplate.exchange("http://localhost:8084/companies", HttpMethod.POST, httpEntity, Void.class);
        return responseEntity.getStatusCode();

    }
}
