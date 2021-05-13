package com.itxwl.domain.event;

import com.itxwl.domain.ServiceLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author xueWenLiang
 * @date 2021/5/11 19:06
 * @Description 系统运行日志监听事件
 */
public class XwlLogEvent extends ApplicationEvent {

    public XwlLogEvent(ServiceLog source) {
        super(source);
    }
}
