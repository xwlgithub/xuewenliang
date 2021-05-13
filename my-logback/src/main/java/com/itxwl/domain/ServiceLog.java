package com.itxwl.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author xueWenLiang
 * @date 2021/5/11 19:05
 * @Description 描述信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("xwl_log_info")
public class ServiceLog {
    private Long id;
    //接口名称
    private String interfaceName;
    //接口路径
    private String interface_url;
    //方法类型
    private String methodType;
    //方法名称
    private String methodName;
    //访问路径
    private String serviceIp;
    //接口名称
    private String apiName;
    //参数体
    private String params;
    //创建时间
    private Date createTime;
    //用户名
    private String userName;
}
