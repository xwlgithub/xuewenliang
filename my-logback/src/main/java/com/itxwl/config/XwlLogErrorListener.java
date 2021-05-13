package com.itxwl.config;

import com.itxwl.domain.ErrorLog;
import com.itxwl.domain.event.XwlLogErrorEvent;
import com.itxwl.mapper.XwlLogErrorMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author xueWenLiang
 * @date 2021/5/12 14:59
 * @Description 异常日志监听事件->
 */

@Component
@AllArgsConstructor
@Slf4j
public class XwlLogErrorListener {

    private final XwlLogErrorMapper xwlLogErrorMapper;

    @Async
    @Order
    @EventListener(XwlLogErrorEvent.class)
    public void xwlLogErrorInsert(XwlLogErrorEvent xwlLogErrorEvent){
        ErrorLog errorLog = (ErrorLog) xwlLogErrorEvent.getSource();
        xwlLogErrorMapper.insert(errorLog);
    }
}
