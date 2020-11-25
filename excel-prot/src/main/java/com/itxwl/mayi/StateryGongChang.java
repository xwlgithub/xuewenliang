package com.itxwl.mayi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Auther: 薛
 * @Date: 2020/11/21 17:17
 * @Description:
 */
// TODO 具体调用执行策略工厂分发类
@Component
public class StateryGongChang {
    private static final String STATERY_PARAM="StaterParam";
    @Autowired
    private RedisTemplate redisTemplate;
    private static  StateryGongChang stateryGongChang;
    @PostConstruct
    public void init(){
        stateryGongChang=this;
        stateryGongChang.redisTemplate=this.redisTemplate;
    }
    /**
     * test -> 测试
     *
     * @param pageCodeType
     * @return
     */
    public static MyStatery getStatery(String pageCodeType) {
        MyStatery myStatery = null;
        try {
            String stateryName = StateryEnum.valueOf(pageCodeType).getStateryName();
            myStatery = (MyStatery) Class.forName(stateryName).newInstance();
            return myStatery;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return myStatery;
    }

    /**
     * 使用自动注入
     *
     * @param pageCodeType
     * @return
     */
    public static MyStatery getStateryBean(String pageCodeType) throws RuntimeException {
        MyStatery myStatery = null;
        String stateryName = null;
        try {
            StateryEnum stateryEnum = StateryEnum.valueOf(pageCodeType);
            stateryName = stateryEnum.getStateryName();
            stateryGongChang.redisTemplate.opsForValue().set(STATERY_PARAM,stateryEnum.getDomainClassPath());
        } catch (Exception e) {
           throw  new RuntimeException("暂无此策略模式");
        }
        myStatery = SpringUtils.getBean(stateryName, MyStatery.class);
        return myStatery;
    }
}
