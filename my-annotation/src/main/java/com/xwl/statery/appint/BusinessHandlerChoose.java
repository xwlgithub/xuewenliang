package com.xwl.statery.appint;

import com.xwl.statery.annotation.BusinessProcessAnnotation;
import com.xwl.statery.factory.BusinessProcessFactory;
import com.xwl.statery.impl.BusinessProcessImpl;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xueWenLiang
 * @date 2021/9/2 14:40
 * @Description 具体执行策略指定类
 */
public class BusinessHandlerChoose {
    private Map<BusinessProcessAnnotation, BusinessProcessFactory> factoryMap;

    public void setBusinessHandlerMap(List<BusinessProcessFactory> businessProcessFactoryList){
        HashMap<BusinessProcessAnnotation, BusinessProcessFactory> factoryHashMap = new HashMap<>();
        for (BusinessProcessFactory businessProcessFactory : businessProcessFactoryList) {
            BusinessProcessAnnotation annotation = AnnotationUtils.findAnnotation(businessProcessFactory.getClass(), BusinessProcessAnnotation.class);
            factoryHashMap.put(annotation, businessProcessFactory);
        }
        factoryMap=factoryHashMap;
    }

    public <R, T> BusinessProcessFactory<R, T> businessHandlerChooser(String type, String source) {
        BusinessProcessAnnotation businessProcess = new BusinessProcessImpl(type, source);
        BusinessProcessFactory businessProcessFactory = factoryMap.get(businessProcess);
        return businessProcessFactory;
    }

}
