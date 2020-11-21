package com.itxwl.mayi;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Auther: 薛
 * @Date: 2020/11/21 17:11
 * @Description:
 */
@Service
@Qualifier("weiXinStatery")
public class WeiXinStatery implements MyStatery {
    @Override
    public String startData() {
        return "调用微信。。。";
    }
}
