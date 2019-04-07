package com.tim3.Auth.controllers;

import com.tim3.Auth.models.Auth;
import com.tim3.Auth.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<Auth> register(@Validated({Auth.RegistrationValidation.class}) @RequestBody Auth auth) {
        Auth existingAuth = authService.getByUsername(auth.getUsername());
        if(existingAuth != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        Auth registeredUser = authService.register(auth);
        return new ResponseEntity<Auth>(registeredUser, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated({Auth.LoginValidation.class}) @RequestBody Auth auth){
        Auth loggedAuth = authService.login(auth);
        if(loggedAuth == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>("Bearer " + authService.generateToken(loggedAuth.getId().toString(), loggedAuth.getUsername()) , HttpStatus.OK);
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
