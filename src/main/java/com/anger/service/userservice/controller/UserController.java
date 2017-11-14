package com.anger.service.userservice.controller;

import com.anger.service.userservice.domain.User;
import com.anger.service.userservice.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/users/{login}")
    public User retrieveUser(@PathVariable String login) {
        User user = repository.findByLogin(login);

        return user;
    }

}