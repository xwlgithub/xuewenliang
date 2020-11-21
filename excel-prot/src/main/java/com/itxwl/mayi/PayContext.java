package com.itxwl.mayi;

import org.springframework.stereotype.Component;

/**
 * @Auther: 薛
 * @Date: 2020/11/21 17:15
 * @Description:
 */
@Component
public class PayContext {

    public String toGetData(String pageCode){
        //MyStatery statery = StateryGongChang.getStatery(pageCode);
        MyStatery stateryBean = null;
        try {
            stateryBean = StateryGongChang.getStateryBean(pageCode);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
        if (stateryBean==null){
            return "暂无此执行策略模式,请更换其它哦~";
        }
        String s = stateryBean.startData();
        return s;
    }
}
