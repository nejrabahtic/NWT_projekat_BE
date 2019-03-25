package com.tim3.Auth;

import com.tim3.Auth.repositories.AuthRepository;
import com.tim3.Auth.services.AuthService;
import com.tim3.Auth.services.SeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableAutoConfiguration
public class AuthApplication {
	@Autowired
	private SeederService seederService;

	@EventListener(ApplicationReadyEvent.class)
	public void seedDatabase(){
		seederService.seedAuthTable();
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
