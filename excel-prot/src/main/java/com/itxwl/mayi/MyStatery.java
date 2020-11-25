package com.itxwl.mayi;

import com.itxwl.result.R;

import java.util.Map;

/**
 * 策略实现接口类
 */
public interface MyStatery {
    /**
     * 具体策略统一执行业务方法
     * @return
     */
    R startData(Map<String,Object> params);
}
