package com.itxwl.demo;

import com.itxwl.controller.PlanDetail;
import lombok.Data;

import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/11/21 13:27
 * @Description:
 */

/**
 * 策略接口类
 */
public  interface CurrencyDataDto {

    /**
     * 策略通用方法
     * @return
     */
     String getDatas();

}
