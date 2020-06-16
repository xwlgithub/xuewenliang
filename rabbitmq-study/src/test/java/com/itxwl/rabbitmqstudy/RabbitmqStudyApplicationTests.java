package com.itxwl.rabbitmqstudy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqStudyApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void contextLoads() {
        rabbitTemplate.convertAndSend("xwl_exchange","item","李四");
        System.out.println("发送了一条消息");
    }

}
