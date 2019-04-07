package com.tim3.User.services;

import com.tim3.User.models.User;
import com.tim3.User.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SeederService {

    @Autowired
    private UserRepository userRepository;

}
