package com.example.finalprojectspring;

import com.example.finalprojectspring.model.entity.User;
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
public class TestOrdersPage {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOrdersPage() throws Exception {
        User user = User.createUser(26,"Test", "Test123", User.Role.User, User.UserStatus.Unblocked);
        mockMvc.perform(get("/app/user/orderPage").sessionAttr("user",user))
                .andDo(print())
                .andExpect(forwardedUrl("/WEB-INF/jsp/user/orders.jsp"));
    }
}
