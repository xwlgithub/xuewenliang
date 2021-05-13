package com.itxwl.domain.event;

import com.itxwl.domain.ErrorLog;
import com.itxwl.domain.ServiceLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author xueWenLiang
 * @date 2021/5/12 14:27
 * @Description 系统业务异常监听类
 */
public class XwlLogErrorEvent extends ApplicationEvent {


    public XwlLogErrorEvent(ErrorLog source) {
        super(source);
    }
}
