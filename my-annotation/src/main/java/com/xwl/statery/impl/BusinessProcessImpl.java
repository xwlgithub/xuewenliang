package com.xwl.statery.impl;

import com.xwl.statery.annotation.BusinessProcessAnnotation;

import java.lang.annotation.Annotation;

/**
 * @author xueWenLiang
 * @date 2021/9/2 14:35
 * @Description 描述信息
 */
public class BusinessProcessImpl implements BusinessProcessAnnotation {

    private final String type;
    private final String source;

    public BusinessProcessImpl(String type, String source) {
        this.type = type;
        this.source = source;
    }

    @Override
    public String type() {
        return null;
    }

    @Override
    public String source() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return BusinessProcessAnnotation.class;
    }

    /**
     * SynthesizedMergedAnnotationInvocationHandler 里面方法实现computeHashCode
     * AnnotationInvocationHandler 注解源码实现
     * @return
     */
    @Override
    public int hashCode() {
//        int hashCode = Objects.hash(type, source);
        int hashCode = 0;

        //(127 * attribute.getName().hashCode()) ^ getValueHashCode(value);
        System.out.println(127 * "type".hashCode()+"参数hash"+"值value-hash"+type.hashCode());
        System.out.println((127 * "type".hashCode())+type.hashCode());
        hashCode += (127 * "type".hashCode()) ^ type.hashCode();
        System.out.println("第一次hash"+hashCode);
        hashCode += (127 * "source".hashCode()) ^ source.hashCode();
        System.out.println("第二次hash"+hashCode);
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
     if (!(o instanceof  BusinessProcessAnnotation)){
         return false;
     }
        BusinessProcessAnnotation businessProcessAnnotation = (BusinessProcessAnnotation) o;
     return type.equals(businessProcessAnnotation.type())&& source.equals(businessProcessAnnotation.source());
    }

}
