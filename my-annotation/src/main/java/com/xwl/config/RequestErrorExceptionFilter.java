package com.xwl.config;

import com.xwl.config.exception.XwlException;
import com.xwl.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xueWenLiang
 * @date 2021/4/30 14:35
 * @Description 自定义拦截异常返回
 */
@ControllerAdvice
@ResponseBody
public class RequestErrorExceptionFilter  {
    /**
     * 业务异常返回拦截并返回
     * @param xwlException
     * @return
     */
    @ExceptionHandler(XwlException.class)
    public R returnException(XwlException xwlException){
        return R.exceptionReturn(xwlException.getXwlResultErrorEnum());

    }
}
