package com.itxwl.config;

import com.itxwl.annotation.XwlLog;
import com.itxwl.domain.ServiceLog;
import com.itxwl.domain.event.XwlLogEvent;
import com.itxwl.utils.SpringContextHolder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author xueWenLiang
 * @date 2021/5/11 15:40
 * @Description 切面拦截日志注解封装日志运行信息体
 */
@Aspect
@Component
@Order(1)
@Slf4j
@SuppressWarnings("all")
public class XwlLogAspect {

    private static XwlLogAspect xwlLogAspect=new XwlLogAspect();

    @Autowired
    private RedisTemplate redisTemplate;
    @PostConstruct
    public void init(){
        xwlLogAspect.redisTemplate=redisTemplate;
    }
    /**
     * 切面拦截 构建异常信息体
     * @param point
     * @param xwlLog
     * @return
     */
    @Around("@annotation(xwlLog)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint point, XwlLog xwlLog) {
        ServiceLog serviceLog = new ServiceLog();
        //类名称
        String className = point.getTarget().getClass().getName();
        //方法名称
        String methodName = point.getSignature().getName();
        //获取request域
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //访问路径url
        String requestURI = request.getRequestURI();
        StringBuffer stringBuffer=new StringBuffer();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String key : parameterMap.keySet()) {
            //如果参数值不存在值->直接添加参数||反之前缀添加符号
            if (StringUtils.isEmpty(stringBuffer)){
                stringBuffer.append(key+":"+parameterMap.get(key)[0]);
            }else {
                stringBuffer.append(","+key+":"+parameterMap.get(key)[0]);
            }

        }
        //IP地址
        String remoteAddr = request.getRemoteAddr();
        //获取请求方式
        String methodType = request.getMethod();
        //创建时间
        // TODO 操作人暂定->可以通过拦截器拦截请求信息->threadLocal存储并获取
        //异常名称->描述
        String describution = xwlLog.describution();
        serviceLog.setInterfaceName(className);
        serviceLog.setMethodName(methodName);
        serviceLog.setInterface_url(requestURI);
        serviceLog.setParams(stringBuffer.toString());
        serviceLog.setServiceIp(remoteAddr);
        serviceLog.setMethodType(methodType);
        serviceLog.setCreateTime(new Date());
        serviceLog.setApiName(describution);
        xwlLogAspect.redisTemplate.opsForValue().set("logKey",describution);
        serviceLog.setUserName("张三");
        SpringContextHolder.publishEvent(new XwlLogEvent(serviceLog));
        return point.proceed();
    }
}
