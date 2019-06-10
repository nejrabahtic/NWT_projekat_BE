package com.tim3.User.services;

import com.tim3.User.models.User;
import com.tim3.User.models.Skill;
import com.tim3.User.repositories.SkillRepository;
import com.tim3.User.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;

@Service
public class SeederService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    public void seedUserTable(){
        ArrayList<User> users = new ArrayList<>();

        users.add(new User(1, "Something about nejra", "nejrabahtic", "nejrabahtic@gmail.com", "+387 00-000-000"));
        users.add(new User(2, "Something about delmond", "delmond", "muhameddelalic@gmail.com", "+387 000-111-222"));
        users.add(new User(3, "Something about thor", "thorasgard", "chris@gmail.com", "+387 111-222-333"));
        users.add(new User(4, "Something about winter soldier", "wintersoldier", "sebastian@gmail.com", "+387 222-333-444"));
        users.add(new User(5, "Something about irfan", "irfanprazina", "irfanp@gmail.com", "+387 333-444-555"));
        users.add(new User(6, "Something about ... and a bit more", "Neko Ime", "irfanp@gmail.com", "+387 333-444-555"));

        if (userRepository.count() == 0) {
            userRepository.saveAll(users);
        }
    }
    public void seedSkillTable(){
        File skillsFromFile = null;
        BufferedReader br = null;
        ArrayList<Skill> skills = new ArrayList<>();
        String name = null;
        try {
            skillsFromFile = ResourceUtils.getFile("classpath:skills.txt");
            br = new BufferedReader(new FileReader(skillsFromFile));
            while( (name = br.readLine()) != null){
                skills.add(new Skill(name.trim()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        if (skillRepository.count() == 0) {
            skillRepository.saveAll(skills);
        }
    }

}
