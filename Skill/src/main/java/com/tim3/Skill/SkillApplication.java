package com.tim3.Skill;

import com.tim3.Skill.services.SeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SkillApplication {
	@Autowired
	private SeederService seederService;

	@EventListener(ApplicationReadyEvent.class)
	public void seedDatabase(){
		seederService.seedSkills();
	}
	public static void main(String[] args) {
		SpringApplication.run(SkillApplication.class, args);
	}

}
