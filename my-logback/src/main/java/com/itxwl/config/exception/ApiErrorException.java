package com.itxwl.config.exception;

import com.itxwl.config.enums.ApiReturnMsgEnmu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author xueWenLiang
 * @date 2021/5/12 14:01
 * @Description 自定义异常拓展类
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApiErrorException  extends RuntimeException{

    private ApiReturnMsgEnmu apiReturnMsgEnmu;
    /**
     * 异常类->用途：后续异常封装部分信息进库使用
     */
    private Exception exception;

    /**
     * 单枚举异常编号及描述信息构造
     * @param apiReturnMsgEnum
     */
    public ApiErrorException(ApiReturnMsgEnmu apiReturnMsgEnum){
        this.apiReturnMsgEnmu=apiReturnMsgEnum;
    }


}
