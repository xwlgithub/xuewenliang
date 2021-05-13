package com.itxwl.config;

import com.itxwl.config.enums.ApiReturnMsgEnmu;
import com.itxwl.config.exception.ApiErrorException;
import com.itxwl.domain.ErrorLog;
import com.itxwl.domain.event.XwlLogErrorEvent;
import com.itxwl.utils.MyUtils;
import com.itxwl.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xueWenLiang
 * @date 2021/5/12 9:50
 * @Description 异常过滤器->封装异常体进库
 */
@Configuration
@ResponseBody
@RestControllerAdvice
public class ExceptionFilterReturnConfig {
    private static ExceptionFilterReturnConfig exceptionFilterReturnConfig=new ExceptionFilterReturnConfig();

    @Autowired
    private RedisTemplate redisTemplate;
    @PostConstruct
    public void init(){
        exceptionFilterReturnConfig.redisTemplate=redisTemplate;
    }
    /**
     * 1.返回具体异常信息体
     * 2.异常日志进库->发起监听事件
     * @param apiErrorException
     * @return
     */
    @ExceptionHandler(ApiErrorException.class)
    @SuppressWarnings("all")
    public ResponseEntity<Map<String, Object>> apiErrorException(ApiErrorException apiErrorException) {
        Map<String, Object> map = new HashMap<>();
        ApiReturnMsgEnmu apiReturnMsgEnmu = apiErrorException.getApiReturnMsgEnmu();
        int code = apiReturnMsgEnmu.getCode();
        String msg = apiReturnMsgEnmu.getMsg();
        //构建异常返回体
        map.put("code", code);
        map.put("msg", msg);
        //发布日志事件
        ErrorLog errorLog = new ErrorLog();
        StackTraceElement traceElement = apiErrorException.getStackTrace()[0];
        //类名称
        String className = traceElement.getClassName();
        //方法名称
        String methodName = traceElement.getMethodName();
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
        errorLog.setInterfaceName(className);
        errorLog.setMethodName(methodName);
        errorLog.setInterface_url(requestURI);
        errorLog.setParams(stringBuffer.toString());
        errorLog.setServiceIp(remoteAddr);
        errorLog.setMethodType(methodType);
        errorLog.setCreateTime(new Date());
        errorLog.setUserName("张三");
        errorLog.setApiName((String) exceptionFilterReturnConfig.redisTemplate.opsForValue().get("logKey"));
        String stackTrace = MyUtils.getStackTrace(apiErrorException);
        System.out.println(stackTrace.length());
        errorLog.setStackTrace(stackTrace);
        //异常描述
        errorLog.setExMsg(msg);
        //行数
        errorLog.setLineNumber(traceElement.getLineNumber());
        SpringContextHolder.publishEvent(new XwlLogErrorEvent(errorLog));
        return ResponseEntity.ok(map);
    }


}
