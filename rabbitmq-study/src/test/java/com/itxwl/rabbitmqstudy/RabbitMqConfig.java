//package com.itxwl.rabbitmqstudy;
//
//import org.springframework.amqp.core.*;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Auther: 薛
// * @Date: 2020/6/16 16:09
// * @Description:
// */
//@Configuration
//public class RabbitMqConfig {
//    //交换机名称
//    public static  String ITEM_TOPIC_EXCHANGE="xwl_exchange";
//    //队列名称
//    public static final String ITEM_QUEUE = "demo_queues";
//    //声明交换机
//    @Bean("itemTopicExchange")
//    public Exchange topicExchange(){
//        return ExchangeBuilder.topicExchange(ITEM_TOPIC_EXCHANGE).durable(true).build();
//    }
//    //声明队列
//    @Bean("itemQueue")
//    public Queue itemQueue(){
//        return QueueBuilder.durable(ITEM_QUEUE).build();
//    }
//    //绑定队列和交换机
//    @Bean
//    public Binding itemQueueExchange(@Qualifier("itemQueue") Queue queue,
//                                     @Qualifier("itemTopicExchange") Exchange exchange){
//        return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
//    }
//}
