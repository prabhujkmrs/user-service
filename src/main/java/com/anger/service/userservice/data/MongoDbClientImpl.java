package com.anger.service.userservice.data;

import com.anger.service.userservice.dao.UserDao;
import com.anger.service.userservice.dto.UserDto;
import org.springframework.stereotype.Service;

/**
 * Created by pjeyamukar on 14/11/2017.
 */
@Service
public class MongoDbClientImpl implements DatabaseClient {


    @Override
    public UserDao getUser(String userId) {
        return null;
    }

    @Override
    public UserDao createUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDao updateUser(UserDto userDto) {
        return null;
    }
}
