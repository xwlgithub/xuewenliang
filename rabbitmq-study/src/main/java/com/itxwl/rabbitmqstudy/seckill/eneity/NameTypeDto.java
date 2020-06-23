package com.itxwl.rabbitmqstudy.seckill.eneity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 薛
 * @Date: 2020/6/23 15:40
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameTypeDto {
    private String userName;
    private Integer shopType;
}
