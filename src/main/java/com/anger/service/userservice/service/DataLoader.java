package com.anger.service.userservice.service;

import com.anger.service.userservice.dao.UserDao;
import com.anger.service.userservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DataLoader {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void loadData(){
        // save a couple of customers
        UserDao userDao = userRepository.findByLogin("1");
        if (userDao == null) {
//            userRepository.save(new UserDao("1", "Smith", "Smith", "Alan", "Smith", "smith@test.com", false,"","","","",null));
//            userRepository.save(new UserDao("2", "Alex", "Alex", "Alex", "Long", "alex@test.com", false,"","","","",null));
//            userRepository.save(new UserDao("3", "Gaj", "Gaj", "Gajan", "Sat", "gajan@test.com", false,"","","","",null));
        }
    }
}