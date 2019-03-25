package com.tim3.Auth.controllers;

import com.tim3.Auth.models.Auth;
import com.tim3.Auth.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
