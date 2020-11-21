package com.itxwl.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Auther: 薛
 * @Date: 2020/11/21 14:14
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 环境角色类
 */
public class EnvironMental {
    //持有一个具体策略对象
    private CurrencyDataDto currencyDataDto;


    //策略集中执行方法
    public  String convertEnvironMental(){
        String datas = this.currencyDataDto.getDatas();
        System.out.println(System.currentTimeMillis()+"来了"+datas);
        return    datas;
    }


}
