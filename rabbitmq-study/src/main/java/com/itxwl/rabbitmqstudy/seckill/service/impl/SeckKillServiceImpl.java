package com.itxwl.rabbitmqstudy.seckill.service.impl;

import com.itxwl.rabbitmqstudy.seckill.service.ISeckKillService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


/**
 * @Auther: 薛
 * @Date: 2020/6/23 15:25
 * @Description:
 */
@Service
@AllArgsConstructor
public class SeckKillServiceImpl implements ISeckKillService {
    //交换机名称
    public static final String ITEM_TOPIC_EXCHANGE = "xwl_exchange";
    public static final String ITEM_ROUKEY = "item.sendKill";
    //引入消息发送API
    private RabbitTemplate rabbitTemplate;
    @Override
    @SneakyThrows
    public void getShopByType(String userName, Integer shopType) {
        //不做任何操作处理~直接进去队列-
        rabbitTemplate.convertAndSend(ITEM_TOPIC_EXCHANGE,ITEM_ROUKEY,userName);
    }
}
