package com.example.user;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.DemoApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class UserControllerTest {

	@Autowired private WebApplicationContext wac;
    private MockMvc mvc;
    
    @Before
    public void setup() {
         mvc = MockMvcBuilders
                   .webAppContextSetup(wac)
                   .build();
    }
    
    @Test
    public void loginTest() throws Exception {
         AuthenticationRequest request = new AuthenticationRequest();
         request.setUsername("user1");
         request.setPassword("pass1");
         
         ObjectMapper om = new ObjectMapper();
         
         mvc.perform(post("/user/login")
                   .contentType(MediaType.APPLICATION_JSON_UTF8)
                   .content(om.writeValueAsString(request)))
              .andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
              .andExpect(jsonPath("$.username", is(request.getUsername().toUpperCase())))
              .andExpect(jsonPath("$.authorities[*].authority", hasItem("USER")))
              ;
    }

}
