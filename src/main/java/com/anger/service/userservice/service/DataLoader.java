package com.anger.service.userservice.service;

import com.anger.service.userservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by gsatkunanandan on 14/11/2017.
 */
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
        userRepository.save(new User(1, "Smith"));
        userRepository.save(new User(2, "Alex"));
    }
}
