package com.itxwl.rabbitmqstudy.seckill.eneity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 薛
 * @Date: 2020/6/23 15:12
 * @Description:
 */
@TableName("order_info")
@Data
@NoArgsConstructor
public class OrderInfo {

    private Long id;
    private String userName;
    private String address;//地址
    private String orderCode;//订单编号
    private Integer comModity;//商品类型

    public OrderInfo(String userName,String orderCode,Integer comModity){
        this.userName=userName;
        this.address="河南卫辉唐庄镇。。。";
        this.orderCode=orderCode;
        this.comModity=comModity;
    }
}
