package com.itxwl.run.service.impl;

import com.itxwl.run.service.DemoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Auther: 薛
 * @Date: 2020/6/2 09:09
 * @Description:
 */
@Service
public class DemoServiceImpl implements DemoService {
    private Logger logger = LogManager.getLogger(DemoServiceImpl.class);

    @Override
    public Integer js(Integer data)  throws RuntimeException{
        logger.fatal("访问接口");
        System.out.println("ddd");
        Integer dd = 5;
        Integer ix = 0;
        if (!StringUtils.isEmpty(data)) {
            try {
                ix = dd / data;
            } catch (Exception e) {
                logger.error("计算失败");
               throw  new RuntimeException("计算失败");
            }
        }
        try {
            int x=1/0;
        } catch (Exception e) {
            logger.error("其它异常");
            throw  new RuntimeException("哈哈哈");
        }
        return ix;
    }
}
