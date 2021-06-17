package com.itxwl.rest;

import com.itxwl.common.BaseIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author xueWenLiang
 * @date 2021/5/28 15:32
 * @Description 测试类
 */
public class UaaSecurityTest   extends BaseIntegrationTest {
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
    @WithMockUser(username = "user",roles = {"USER"})
    @Test
    public void givenRoleUserOrAdmin_thenAccessSuccess() throws Exception {
        mockMvc.perform(get("/api/users/{username}", "user"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    /**
     * 测试 @PostAuthorize("returnObject.username == authentication.name")
     * 查询自己的信息应该成功
     *
     * @throws Exception 异常
     */
    @WithMockUser(username = "user")
    @Test
    public void givenUserRole_whenQueryUserByEmail_shouldSuccess() throws Exception {
        mockMvc.perform(get("/api/users/by-email/{email}", "user@local.dev"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    /**
     * 测试 MANAGER权限
     *
     * @throws Exception 异常
     */
    @WithMockUser(username = "user")
    @Test
    public void givenUserRole_Manager_shouldSuccess() throws Exception {
        mockMvc.perform(get("/api/logins"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
