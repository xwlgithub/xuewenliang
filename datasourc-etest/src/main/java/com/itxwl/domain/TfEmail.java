package com.itxwl.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 薛
 * @Date: 2020/7/20 13:42
 * @Description:
 */
@Data
@TableName("tf_email")
@AllArgsConstructor
@NoArgsConstructor
public class TfEmail {
    private Long id;
    private String sendName;
    private String requestUrl;
    private String errorStack;
    private String serverId;
}
