package com.itxwl.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xueWenLiang
 * @date 2021/4/29 17:04
 * @Description 描述信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("xwl_user")
public class XwlUser {
    @JsonSerialize(nullsUsing = ToStringSerializer.class)
    private Long id;
    private String userName;
    private String userAddress;
}
