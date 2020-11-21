package com.itxwl.mayi;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Auther: 薛
 * @Date: 2020/11/21 17:11
 * @Description:
 */
@Service
@Qualifier("aliyunStatery")
public class AliyunStatery implements MyStatery {
    @Override
    public String startData() {
        return "支付宝。。";
    }
}
