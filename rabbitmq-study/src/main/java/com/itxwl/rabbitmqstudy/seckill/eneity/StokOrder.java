package com.itxwl.rabbitmqstudy.seckill.eneity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 薛
 * @Date: 2020/6/23 15:16
 * @Description:
 */
@TableName("stok_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StokOrder {
    private Long id;
    private String comMondity;//商品类型
    private Integer stockCount;//库存
}
