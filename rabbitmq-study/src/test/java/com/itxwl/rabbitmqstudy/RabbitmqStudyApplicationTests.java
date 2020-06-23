package com.itxwl.rabbitmqstudy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqStudyApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() throws InterruptedException {
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setExpiration("5000");
//        Message message = new Message("张三".getBytes(), messageProperties);
        for (int i = 0; i < 50
                ; i++) {
            rabbitTemplate.convertAndSend("xwl_exchange", "item.ss", "1");
        }
        System.out.println("发送了一条消息");
    }
}
