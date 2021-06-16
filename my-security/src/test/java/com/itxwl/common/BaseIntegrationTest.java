package com.itxwl.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author xueWenLiang
 * @date 2021/6/15 14:35
 * @Description 描述信息
 */
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIntegrationTest {
    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected ObjectMapper objectMapper;
}
