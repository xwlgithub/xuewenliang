package com.itxwl.demo;

import org.springframework.stereotype.Service;

/**
 * @Auther: 薛
 * @Date: 2020/11/21 14:18
 * @Description:
 */
@Service
public class ChildrenServiceA implements CurrencyDataDto {
    @Override
    public String getDatas() {
        return "我是张三啊";
    }
}
