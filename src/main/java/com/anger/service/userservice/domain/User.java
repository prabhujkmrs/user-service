package com.anger.service.userservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class User {

    @Id
    private int id;

    @NotNull
    @Size(min = 1, max = 50)
    @Indexed
    private String login;

    public User(int id, String login) {
        super();
        this.id = id;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}