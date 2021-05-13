package com.xwl.config;

import com.xwl.annotation.RequestUser;
import com.xwl.config.enums.XwlResultErrorEnum;
import com.xwl.config.exception.XwlException;
import com.xwl.domain.XwlUser;
import com.xwl.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xueWenLiang
 * @date 2021/4/30 10:10
 * @Description 自定义注解控制器,解析token获取用户信息
 */
@AllArgsConstructor
@Component
public class XwlUserArgumentResolver  implements HandlerMethodArgumentResolver {
    /**
     * 执行判断是否满足使用规范
     * @param methodParameter
     * @return true即跳出进行业务处理(resolveArgument)|| false不执行
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //是否是注解元素
        boolean isTrueRequestUser = methodParameter.hasParameterAnnotation(RequestUser.class);
        //是否注在指定用户信息对象
        boolean isAnnotationXwlUser = methodParameter.getParameterType().isAssignableFrom(XwlUser.class);
        return isTrueRequestUser==isAnnotationXwlUser;
    }

    /**
     * 解析token封装用户信息
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        XwlUser xwlUser= null;
        try {
            xwlUser = JwtUtils.getUserParameters(request);
        } catch (Exception e) {
            throw new XwlException(XwlResultErrorEnum.FORBIDDEN);
        }
        //不排除以下情况->抛出无鉴权权限
        if (ObjectUtils.isEmpty(xwlUser))throw new XwlException(XwlResultErrorEnum.FORBIDDEN);
        return xwlUser;
    }
}
