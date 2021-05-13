package com.itxwl.config;

import com.itxwl.domain.ServiceLog;
import com.itxwl.domain.event.XwlLogEvent;
import com.itxwl.mapper.XwlLogInfoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author xueWenLiang
 * @date 2021/5/11 19:13
 * @Description 日志监听事件->
 */
@Component
@AllArgsConstructor
@Slf4j
public class XwlLogListener {

    private final XwlLogInfoMapper xwlLogInfoMapper;

    @Async
    @Order
    @EventListener(XwlLogEvent.class)
    public void saveXwlLogInfo(XwlLogEvent xwlLogEvent){
        ServiceLog serviceLog = (ServiceLog) xwlLogEvent.getSource();
        xwlLogInfoMapper.insert(serviceLog);
    }
}
