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

    @Override
    public int hashCode() {
//        int hashCode = Objects.hash(type, source);
        int hashCode = 0;
        hashCode += (127 * "type".hashCode()) ^ type.hashCode();
        hashCode += (127 * "source".hashCode()) ^ source.hashCode();

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
