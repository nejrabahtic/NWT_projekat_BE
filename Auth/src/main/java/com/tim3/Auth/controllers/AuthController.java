package com.tim3.Auth.controllers;

import com.tim3.Auth.models.Auth;
import com.tim3.Auth.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @GetMapping
    public ResponseEntity<List<Auth>> getAll(){
        return new ResponseEntity<>(authService.getAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<String> register(@Validated({Auth.RegistrationValidation.class}) @RequestBody Auth auth) {
        Auth existingAuth = authService.getByUsername(auth.getUsername());
        if(existingAuth != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        Auth registeredUser = authService.register(auth);

        HttpStatus httpStatus = null;
        if( registeredUser.getRole().equals("user")) {
            httpStatus = authService.createUser(registeredUser.getId());
        } else {
            httpStatus = authService.createCompany(registeredUser.getId());
        }
        if(!httpStatus.is2xxSuccessful())
            return new ResponseEntity<>(httpStatus);

        return new ResponseEntity<>("Bearer " + authService.generateToken(registeredUser.getId().toString(), registeredUser.getUsername(), registeredUser.getRole()) , HttpStatus.OK);
    }
    
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated({Auth.LoginValidation.class}) @RequestBody Auth auth){
        Auth loggedAuth = authService.login(auth);
        if(loggedAuth == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>("Bearer " + authService.generateToken(loggedAuth.getId().toString(), loggedAuth.getUsername(), loggedAuth.getRole()) , HttpStatus.OK);
    }
    @PostMapping("/token")
    public ResponseEntity<String> validateToken(@RequestBody String token){
        if(!token.startsWith("Bearer "))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        System.out.println(token.substring(7));
        String id = authService.validToken(token.substring(7));
        if(id == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Auth> getById(@PathVariable Integer id){
        Auth auth = authService.getById(id);
        if(auth == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(auth, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        Auth auth = authService.getById(id);
        if(auth == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        authService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
