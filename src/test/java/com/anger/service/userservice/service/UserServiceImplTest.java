package com.anger.service.userservice.service;

import com.anger.service.userservice.config.SpringMongoConfiguration;
import com.anger.service.userservice.dao.UserDao;
import com.anger.service.userservice.data.DatabaseClient;
import com.anger.service.userservice.data.MongoDbClientImpl;
import com.anger.service.userservice.dto.UserDto;
import com.anger.service.userservice.exception.ConflictException;
import com.anger.service.userservice.exception.InternalServerErrorException;
import com.anger.service.userservice.exception.NotFoundException;
import com.google.common.collect.ImmutableMap;
import net.bytebuddy.matcher.ElementMatcher;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by gperry on 04/07/2017.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {SpringMongoConfiguration.class})
public class UserServiceImplTest {

    private static final String USER_ID = "123456789";

    @Mock
    private DatabaseClient databaseClient;

    @Mock
    private UserService userService;


    private MongoDbClientImpl mongoDbClient;

    @Before
    public void setUp() throws Exception {
        mongoDbClient = new MongoDbClientImpl();
    }

    @Test(expected = NotFoundException.class)
    public void whenGetUserThrowsNotFoundExceptionThenRethrowIt() {
        when(mongoDbClient.getUser(USER_ID)).thenThrow(NotFoundException.class);

        mongoDbClient.getUser(USER_ID);
    }

    @Test(expected = InternalServerErrorException.class)
    public void whenGetUserThrowsInternalServerErrorExceptionThenRethrowIt() {
        when(mongoDbClient.getUser(USER_ID)).thenThrow(InternalServerErrorException.class);

        mongoDbClient.getUser(USER_ID);
    }

    @Test
    public void whenGetUserReturnsValidUserDaoThenReturnUserDto() {

        UserDto userDto = getUserDto();

        when(databaseClient.getUser(USER_ID)).thenReturn(userDto);

        UserDto result = mongoDbClient.getUser(USER_ID);

        assertThat(result.getUserId(), Is.is(equalTo(userDto.getUserId())));
        assertThat(result.getFirstName(), Is.is(equalTo(userDto.getFirstName())));
        assertThat(result.getLastName(), Is.is(equalTo(userDto.getLastName())));
        assertThat(result.getEmail(), Is.is(equalTo(userDto.getEmail())));
    }

    @Test(expected = ConflictException.class)
    public void whenCreateUserThrowsNotConflictErrorExceptionThenRethrowIt() {
        when(databaseClient.getUser(USER_ID)).thenThrow(ConflictException.class);

        mongoDbClient.getUser(USER_ID);
    }

    @Test(expected = InternalServerErrorException.class)
    public void whenCreateUserThrowsInternalServerErrorExceptionThenRethrowIt() {
        when(databaseClient.getUser(USER_ID)).thenThrow(InternalServerErrorException.class);

        mongoDbClient.getUser(USER_ID);
    }

//    @Test
//    public void whenCreateUserReturnsValidUserDaoThenReturnUserDto() {
//
//        UserDto userDto = UserDto.builder().build();
//        UserDao userDao = getUserDao();
//
//        when(databaseClient.createUser(any(UserDto.class))).thenReturn(userDao);
//
//        UserDto result = mongoDbClient.createUser(userDto);
//
//        assertThat(result.getUserId(), Is.is(equalTo(userDao.getUserId())));
//        assertThat(result.getFirstName(), Is.is(equalTo(userDao.getFirstName())));
//        assertThat(result.getLastName(), Is.is(equalTo(userDao.getLastName())));
//        assertThat(result.getEmail(), Is.is(equalTo(userDao.getEmail())));
//
//    }
//
//
//    @Test(expected = NotFoundException.class)
//    public void whenUpdateUserThrowsNotConflictErrorExceptionThenRethrowIt() {
//        when(databaseClient.getUser(USER_ID)).thenThrow(NotFoundException.class);
//
//        mongoDbClient.getUser(USER_ID);
//    }
//
//    @Test(expected = InternalServerErrorException.class)
//    public void whenUpdateUserThrowsInternalServerErrorExceptionThenRethrowIt() {
//        when(databaseClient.getUser(USER_ID)).thenThrow(InternalServerErrorException.class);
//
//        mongoDbClient.getUser(USER_ID);
//    }
//    @Test
//    public void shouldUpdateUserDetails() {
//
//        UserDto userDto = UserDto.builder().build();
//        UserDao userDao = getUserDao();
//
//        when(databaseClient.updateUser(any(UserDto.class))).thenReturn(userDao);
//
//        UserDto updatedDto = mongoDbClient.updateUser(userDto);
//        verify(databaseClient, times(1)).updateUser(userDto);
//
//        assertEquals(userDao.getUserId(), updatedDto.getUserId());
//        assertEquals(userDao.getFirstName(), updatedDto.getFirstName());
//        assertEquals(userDao.getLastName(), updatedDto.getLastName());
//        assertEquals(userDao.getEmail(), updatedDto.getEmail());
//
//    }

    private UserDao getUserDao() {
        UserDao userDao = new UserDao();
        userDao.setUserId("test");
        userDao.setFirstName("test");
        userDao.setLastName("test");
        userDao.setEmail("test");
        return userDao;
    }

    private UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUserId("test");
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userDto.setEmail("test");
        return userDto;
    }
}
