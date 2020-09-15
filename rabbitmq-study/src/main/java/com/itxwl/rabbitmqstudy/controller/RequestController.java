package com.itxwl.rabbitmqstudy.controller;

import com.rabbitmq.client.BuiltinExchangeType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 薛
 * @Date: 2020/6/20 16:02
 * @Description:
 */
@RestController
@RequestMapping("/d")
//implements RabbitTemplate.ConfirmCallback
public class RequestController  {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
//    @Autowired
//    public RequestController(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//        rabbitTemplate.setConfirmCallback(this);
//    }
    @GetMapping("demo/{id}")
    public String send(@PathVariable("id") Integer id) {
        redisTemplate.opsForValue().set("stockCount",10);
        //        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setExpiration("5000");
//        Message message = new Message("张三".getBytes(), messageProperties);
        for (int i = 0; i < 50
                ; i++) {
            //CorrelationData correlationData = new CorrelationData("1");
            rabbitTemplate.convertAndSend("xwl_exchange", "item.ss", "1");
        }
        return "success";
    }
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean b, String s) {
//        System.out.println("回调开始~~~");
//        System.out.println(correlationData);
//        System.out.println(b);
//        System.out.println(s);
//        System.out.println("回调结束~~~");
//
//    }
}
