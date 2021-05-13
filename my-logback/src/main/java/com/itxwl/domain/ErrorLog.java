package com.itxwl.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

/**
 * @author xueWenLiang
 * @date 2021/5/12 14:25
 * @Description 描述信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("xwl_log_error")
public class ErrorLog extends ServiceLog {

    private String stackTrace;
    private String exMsg;
    private Integer lineNumber;

    /**
     * 异常体->不参与业务剖析
     */
    @JsonIgnore
    @TableField(exist = false)
    private Exception exception;

}
