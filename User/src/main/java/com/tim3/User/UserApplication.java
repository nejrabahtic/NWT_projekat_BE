package com.tim3.User;

import com.tim3.User.services.SeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class UserApplication {

	@Autowired
	private SeederService seederService;

	@EventListener(ApplicationReadyEvent.class)
	public void seedDatabase(){
		seederService.seedUserTable();
//		seederService.seedRequestTable();
	}

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
