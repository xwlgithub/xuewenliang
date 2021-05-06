package com.xwl.config.exception;

import com.xwl.config.enums.XwlResultErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author xueWenLiang
 * @date 2021/4/30 14:20
 * @Description 描述信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XwlException extends RuntimeException {
    /**
     * 枚举异常控制
     */
    private XwlResultErrorEnum xwlResultErrorEnum;

}
