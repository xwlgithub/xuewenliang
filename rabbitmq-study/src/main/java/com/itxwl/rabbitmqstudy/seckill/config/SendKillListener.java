package com.itxwl.rabbitmqstudy.seckill.config;

import com.itxwl.rabbitmqstudy.seckill.eneity.OrderInfo;
import com.itxwl.rabbitmqstudy.seckill.eneity.StokOrder;
import com.itxwl.rabbitmqstudy.seckill.mapper.OrderInfoMapper;
import com.itxwl.rabbitmqstudy.seckill.mapper.StokOrderMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 薛
 * @Date: 2020/6/23 16:15
 * @Description:
 */
@Component
@Configuration
@SuppressWarnings("ALL")
public class SendKillListener {
    //交换机名称
    public static final String ITEM_TOPIC_EXCHANGE = "xwl_exchange";
    //队列名称
    public static final String ITEM_QUEUE = "xwl_queue";
    private RedisTemplate re;
    private StokOrderMapper stokOrderMapper;
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StokOrderMapper sr;
    @Autowired
    private OrderInfoMapper oo;

    @PostConstruct
    public void init() {
        this.re = redisTemplate;
        this.orderInfoMapper = oo;
        this.stokOrderMapper = sr;
    }

    /**
     * 监听主队列~
     *
     * @param message
     * @param map
     * @param channel
     * @throws InterruptedException
     * @throws IOException
     */
    @RabbitListener(queues = "xwl_queue")
    public void sendMiss(Message message, @Headers Map<String, Object> map, Channel channel) throws InterruptedException, IOException {
        String msg = new String(message.getBody(), "UTF-8");
        Integer shopCount=0;
        //第一个请求进来获取库存-先去缓存redis找对应key值如果没有发送一个连接查询后续无需再次获取库存
        if (StringUtils.isEmpty(re.opsForValue().get("stockCount"))) {
            re.opsForValue().set("stockCount", stokOrderMapper.findCountByShopType(1), 60, TimeUnit.MINUTES);
            shopCount = ((Integer) re.opsForValue().get("stockCount"));
        }else {
            //自减缓存内库存量- 每次减-
             shopCount = ((Integer) re.opsForValue().get("stockCount"))-1;
        }
        //如果库存量小于等于0即已经抢完
        if (shopCount<= 0) {
            //即放入死信队列-推送后续队列内消息即为抢单失败-
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            //返回 不做后续处理
            return;
        }
        //如果库存数不为0即没有抢完
        //数据库存储订单
        orderInfoMapper.insert(new OrderInfo(msg, UUID.randomUUID().toString(), 1));
        //设置缓存库存量key过期时间-redis自行删除（赋值减值）
        re.opsForValue().set("stockCount", shopCount, 60, TimeUnit.MINUTES);
        //手动设置ACK接收确认当前消息消费完毕
        ;
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println(msg + "抢购成功,恭喜!");
    }

    /**
     * 监听死信队列-即推送抢单失败
     *
     * @param message
     * @param map
     * @param channel
     * @throws InterruptedException
     * @throws IOException
     */
    @RabbitListener(queues = "deal_queue")
    public static void sendMiss2(Message message, @Headers Map<String, Object> map, Channel channel) throws InterruptedException, IOException {
        String msg = new String(message.getBody(), "UTF-8");
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println(msg + "商品已被抢空~下次再来");
    }
}
