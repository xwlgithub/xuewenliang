package com.itxwl.rabbitmqstudy.seckill.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 薛
 * @Date: 2020/6/16 16:09
 * @Description:
 */
@Component
@Configuration
@SuppressWarnings("ALL")
public class RabbitMqConfig {
    //交换机名称
    public static  String ITEM_TOPIC_EXCHANGE="xwl_exchange";
    //队列名称
    public static final String ITEM_QUEUE = "xwl_queue";
    //声明交换机
    @Bean("xwlTopicExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(ITEM_TOPIC_EXCHANGE).durable(true).build();
    }
    //声明主队列
    @Bean("xwlQueue")
    public Queue itemQueue(){
        Map<String, Object> args = new HashMap<>();
        //声明死信交换器
        args.put("x-dead-letter-exchange", "deal_exchange");
        //声明死信路由键
        args.put("x-dead-letter-routing-key", "DelayKey");
        //声明主队列如果发生堵塞或其它-10秒自动消费消息
        args.put("x-message-ttl",10000);
        return QueueBuilder.durable(ITEM_QUEUE).withArguments(args).build();
    }
    //主队列绑定交换机以及-路由(此处采用TOPC通配符)
    @Bean
    public Binding itemQueueExchange(@Qualifier("xwlQueue") Queue queue,
                                     @Qualifier("xwlTopicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
    }
    //声明死信队列
    @Bean("dealQueue")
    public Queue dealQueue(){
        return QueueBuilder.durable("deal_queue").build();
    }
    //声明死信交换机
    @Bean("dealExchange")
    public Exchange dealExchange(){
        return ExchangeBuilder.topicExchange("deal_exchange").durable(true).build();
    }
    //死信队列绑定交换机以及路由key
    @Bean
    public Binding dealQueueExchange(@Qualifier("dealQueue") Queue queue,
                                     @Qualifier("dealExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("DelayKey").noargs();
    }
}
