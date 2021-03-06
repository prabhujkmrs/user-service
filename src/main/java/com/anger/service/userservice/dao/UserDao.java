package com.anger.service.userservice.dao;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Created by pjeyamukar on 14/11/2017.
 */
@Getter
@Setter
@Data
public class UserDao {

    private String userId;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private boolean activated = false;

    private String langKey;

    private String imageUrl;

    private String activationKey;

    private String resetKey;

    private Instant resetDate = null;

}
