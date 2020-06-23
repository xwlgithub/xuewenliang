package com.itxwl.rabbitmqstudy.springrabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 薛
 * @Date: 2020/6/16 16:09
 * @Description:
 */
//@Component
//@Configuration
public class RabbitMqConfig {
    //交换机名称
    public static  String ITEM_TOPIC_EXCHANGE="xwl_exchange";
    //队列名称
    public static final String ITEM_QUEUE = "xwl_queue";
    @Autowired
    private CachingConnectionFactory connectionFactory;
    //声明交换机
    @Bean("xwlTopicExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(ITEM_TOPIC_EXCHANGE).durable(true).build();
    }
    //声明队列
    @Bean("xwlQueue")
    public Queue itemQueue(){
        Map<String, Object> args = new HashMap<>(3);
        //声明死信交换器
        args.put("x-dead-letter-exchange", "deal_exchange");
        //声明死信路由键
        args.put("x-dead-letter-routing-key", "DelayKey");
        //声明队列消息过期时间
        args.put("x-message-ttl", 5000);
        return QueueBuilder.durable(ITEM_QUEUE).withArguments(args).build();
    }
    //生产绑定队列和交换机
    @Bean
    public Binding itemQueueExchange(@Qualifier("xwlQueue") Queue queue,
                                     @Qualifier("xwlTopicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
    }
    @Bean("dealQueue")
    public Queue dealQueue(){
        return QueueBuilder.durable("deal_queue").build();
    }
    @Bean("dealExchange")
    public Exchange dealExchange(){
        return ExchangeBuilder.topicExchange("deal_exchange").durable(true).build();
    }
    //生产绑定队列和交换机
    @Bean
    public Binding dealQueueExchange(@Qualifier("dealQueue") Queue queue,
                                     @Qualifier("dealExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("DelayKey").noargs();
    }

    /**
     * 其它队列
     */
//    @Bean("otherQueue")
//    public Queue otherQueue(){
//        return QueueBuilder.durable("otherQueue").build();
//    }
//    //绑定分摊队列到xwl_exchange交互机
//    @Bean
//    public Binding otherQueueExchange(@Qualifier("otherQueue") Queue queue,
//                                     @Qualifier("xwlTopicExchange") Exchange exchange){
//        return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
//    }

//    @Bean(name = "mqConsumerlistenerContainer")
//    public SimpleRabbitListenerContainerFactory mqConsumerlistenerContainer(){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setPrefetchCount(1);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        return factory;
//    }
//    @Bean(name = "mqConsumerlistenerContainerTwo")
//    public SimpleRabbitListenerContainerFactory mqConsumerlistenerContainerTwo(){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setPrefetchCount(10);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        return factory;
//    }
}
