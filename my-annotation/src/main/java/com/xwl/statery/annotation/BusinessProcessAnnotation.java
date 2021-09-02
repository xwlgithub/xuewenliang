package com.xwl.statery.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @author xueWenLiang
 * @date 2021/9/2 14:29
 * @Description 业务注解->策略
 */
//可以被doc类似工具文档化
@Documented
//作用于类,方法,参数上
@Target(ElementType.TYPE)
//程序启动即加载
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface BusinessProcessAnnotation {
    /**\
     * 类型
     * @return
     */
    String type();

    /**
     * key
     * @return
     */
    String source();
}
