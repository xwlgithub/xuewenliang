package com.itxwl.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 薛
 * @Date: 2021/1/20 19:20
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Query {
    private Integer current;
    private Integer size;
}
