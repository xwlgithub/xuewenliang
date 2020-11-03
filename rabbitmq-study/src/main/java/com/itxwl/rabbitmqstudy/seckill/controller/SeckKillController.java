package com.itxwl.rabbitmqstudy.seckill.controller;

import com.itxwl.rabbitmqstudy.seckill.config.RabbitMqConfig;
import com.itxwl.rabbitmqstudy.seckill.service.ISeckKillService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/6/23 15:18
 * @Description:
 */
@RestController
@AllArgsConstructor
@RequestMapping("skill")
public class SeckKillController {
    private RedisTemplate redisTemplate;
    private ISeckKillService seckKillService;
    public List<String> getUsers(){
      return   Arrays.asList("张三","李四","王五","赵六","李珏","郭思","吕布","王月英","嘻哈","田丰");
    }
    /**
     * 模拟抢单-入口
     * @param -用户名
     * @param -商品类型 -此处默认1
     * @return
     */
    @GetMapping("getShopByType")
    public String getShopByType(){
        //为了演示结果需
        redisTemplate.opsForValue().set("stockCount",null);
        getUsers().stream().forEach(name ->{
            seckKillService.getShopByType(name,1);
        });
        return "已经收到您的抢购申请,请稍后留意信息提示结果";
    }
}
