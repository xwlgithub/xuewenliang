package com.itxwl.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author xueWenLiang
 * @date 2021/5/28 15:32
 * @Description 描述信息
 */
@SpringBootTest
public class UaaSecurityTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        mockMvc= MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @WithMockUser
    @Test
    public void getsss() throws Exception {
        mockMvc.perform(
                get("/api/greeting")

        ).andExpect(status().isOk());
    }
}
