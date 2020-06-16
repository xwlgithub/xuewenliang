package com.itxwl.rabbitmqstudy.springrabbitmq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: 薛
 * @Date: 2020/6/16 15:53
 * @Description:
 */
@Component
public class SendNews {
    //交换机名称
    public static final String ITEM_TOPIC_EXCHANGE = "xwl_exchange";
    //队列名称
    public static final String ITEM_QUEUE = "demo_queues";
    //自动创建交换机以及队列
    @RabbitListener(bindings =
    @QueueBinding(value = @Queue(value = ITEM_QUEUE, durable = "true"),
            exchange = @Exchange(value = ITEM_TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC, durable = "true"),
            key = {"item.#"}))
    public void sendMiss(String mmm) {
        System.out.println("接了一条消息" + mmm);
    }
}
