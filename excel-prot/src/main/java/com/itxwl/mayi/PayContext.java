package com.itxwl.mayi;

import com.itxwl.result.R;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: 薛
 * @Date: 2020/11/21 17:15
 * @Description:
 */
@Component
/**
 * 通用方法体获取指定执行策略
 */
public class PayContext {

    public R toGetData(String pageCode, Map<String,Object> params){
        MyStatery stateryBean = null;
        try {
            stateryBean = StateryGongChang.getStateryBean(pageCode);
        } catch (RuntimeException e) {
            return R.error(e.getMessage());
        }
        if (stateryBean==null){
            return R.error("暂无此执行策略模式,请更换其它哦~");
        }
        R data = stateryBean.startData(params);
        return data;
    }
}
