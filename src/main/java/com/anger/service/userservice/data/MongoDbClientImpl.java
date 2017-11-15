package com.anger.service.userservice.data;

import com.anger.service.userservice.dao.UserDao;
import com.anger.service.userservice.dto.UserDto;
import com.anger.service.userservice.service.UserRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pjeyamukar on 14/11/2017.
 */
@Service
public class MongoDbClientImpl implements DatabaseClient {

    @Autowired
    private MongoOperations mongoOps;


    @Autowired
    DozerBeanMapper mapper;

    @Override
    public List<UserDto> getAllUsers() {

        List<UserDto> userDtoList = new ArrayList<>();
        List<UserDao> userDaoList = mongoOps.findAll(UserDao.class);

        for(UserDao userDao : userDaoList) {
            userDtoList.add(mapDaoToDtoObject(userDao));
        }

        return userDtoList;
    }

    @Override
    public UserDto getUser(String userId) {
        // build mongo query
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        UserDao userDaoCopy = mongoOps.findOne(query, UserDao.class);
        return mapDaoToDtoObject(userDaoCopy);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserDao userDaoCopy = mapDtoToDaoObject(userDto);
        mongoOps.save(userDaoCopy);
        return this.getUser(userDaoCopy.getUserId());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserDao userDaoCopy = mapDtoToDaoObject(userDto);
        mongoOps.save(userDaoCopy);
        return this.getUser(userDaoCopy.getUserId());
    }

    @Override
    public void deleteUser(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        mongoOps.remove(query, UserDao.class);
    }

    private UserDto mapDaoToDtoObject(UserDao userDao) {
        return mapper.map(userDao, UserDto.class);
    }

    private UserDao mapDtoToDaoObject(UserDto userDto) {
        return mapper.map(userDto, UserDao.class);
    }
}
