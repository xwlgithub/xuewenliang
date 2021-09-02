package com.xwl.statery.factory;

/**
 * @author xueWenLiang
 * @date 2021/9/2 14:44
 * @Description 策略工厂
 */
public interface BusinessProcessFactory<R,T> {

    /**
     * 统一执行方法体
     * @param t
     * @return
     */
    R businessProcess(T t);
}
