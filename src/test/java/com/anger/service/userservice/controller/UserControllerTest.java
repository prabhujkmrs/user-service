package com.anger.service.userservice.controller;

import com.anger.service.userservice.UserServiceApplicationTests;
import com.anger.service.userservice.domain.User;
import com.anger.service.userservice.service.UserDaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDaoService userDaoService;

    User mockUser= new User(1, "Alex");

    String exampleCourseJson = "{\"id\":\"1\",\"login\":\"Alex\"}";

    @Test
    public void retrieveDetailsForCourse() throws Exception {

        Mockito.when(
                userDaoService.findOne(Mockito.anyInt())).thenReturn(mockUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/users/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{id:1,login:Alex}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}
