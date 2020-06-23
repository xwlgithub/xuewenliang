package com.itxwl.rabbitmqstudy.springrabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

/**
 * @Auther: 薛
 * @Date: 2020/6/16 15:53
 * @Description:
 */
@Component
@Configuration
@SuppressWarnings("ALL")
public class SendNews {
    //交换机名称
    public static final String ITEM_TOPIC_EXCHANGE = "xwl_exchange";
    //队列名称
    public static final String ITEM_QUEUE = "xwl_queue";
    private RedisTemplate re;
    @Autowired
    private  RedisTemplate redisTemplate;
    @PostConstruct
    public void init(){
        this.re=redisTemplate;
    }

    /**
     * 监听队列~~
     *
     * @param
     */
    //自动创建交换机以及队列
//    @RabbitListener(bindings =
//    @QueueBinding(value = @Queue(value = ITEM_QUEUE, durable = "true"),
//            exchange = @Exchange(value = ITEM_TOPIC_EXCHANGE,
//                    type = ExchangeTypes.TOPIC,
//                    durable = "true"),
//            key = {"item.#"}))
    //@RabbitListener(queues = "xwl_queue")
    public  void sendMiss(Message message, @Headers Map<String, Object> map, Channel channel) throws InterruptedException, IOException {
        String msg = new String(message.getBody(), "UTF-8");
        try {
            //手动签收
            //Long dd = (Long) map.get(AmqpHeaders.DELIVERY_TAG);
            //channel.basicAck(dd, false);//确认消息-手动应答
            Integer stockCount = (Integer)re.opsForValue().get("stockCount");
            System.out.println("当前总数"+stockCount);
            stockCount--;
            re.opsForValue().set("stockCount",stockCount);
            System.out.println("减库存后"+stockCount);
            if (stockCount<=0){
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                return;
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("普通接了一条消息" + msg);
        } catch (Exception e) {
            //拒绝消费消息-给其绑定的死信队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
        System.out.println( System.currentTimeMillis());
    }
    //@RabbitListener(queues = "xwl_queue")
    public static void otherMiss(Message message, @Headers Map<String, Object> map, Channel channel) throws InterruptedException, IOException {
        String msg = new String(message.getBody(), "UTF-8");
        try {
            int x = 1 / Integer.parseInt(msg);
            Integer index[] ={1000,7000,2000};
            if (index[(int)(Math.random()*3)]>=7000){
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            }
            //System.out.println(index[(int)(Math.random()*3)]);
            //Thread.sleep(index[(int)(Math.random()*3)]);
            //手动签收
           // Long dd = (Long) map.get(AmqpHeaders.DELIVERY_TAG);
            //channel.basicAck(dd, false);//确认消息-手动应答
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("分摊普通接了一条消息" + msg);
        } catch (Exception e) {
            //拒绝消费消息-给其绑定的死信队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    //@RabbitListener(queues ="deal_queue")
    public static void sendMiss2(Message message, @Headers Map<String, Object> map, Channel channel) throws InterruptedException, IOException {
        String msg = new String(message.getBody(), "UTF-8");
        Long dd = (Long) map.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(dd, false);//确认消息
        System.out.println("死信接了一条消息" + msg);
    }
}
