package com.example.finalprojectspring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestLogin {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCorrectLogin() throws Exception {
        mockMvc.perform(post("/app/guest/login").param("login","Test").param("password", "Test123"))
                .andDo(print())
                .andExpect(redirectedUrl("/app/user/mainPage"));
    }

    @Test
    public void testInvalidLoginOrPassword() throws Exception {
        mockMvc.perform(post("/app/guest/login").param("login","Test").param("password", "Test"))
                .andDo(print())
                .andExpect(redirectedUrl("/app/guest/login"));
    }
}
