package com.xwl.controller;

import com.xwl.annotation.RequestUser;
import com.xwl.config.enums.XwlResultErrorEnum;
import com.xwl.config.exception.XwlException;
import com.xwl.domain.XwlUser;
import com.xwl.mapper.XwlUserMapper;
import com.xwl.utils.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueWenLiang
 * @date 2021/4/29 16:30
 * @Description 描述信息
 */
@RequestMapping("xwluser")
@RestController
@AllArgsConstructor
@Slf4j
public class UserAnnotationController {
    private XwlUserMapper xwlUserMapper;

    @GetMapping("getUserInfo")
    public R<XwlUser> getUserInfo(@RequestParam("sp") Long sp, @RequestUser XwlUser xwlUser) {
        log.info("根据id访问用户信息体");
        System.out.println("获取用户信息" + xwlUser.toString());
        XwlUser xwlUser1 = xwlUserMapper.selectById(sp);
        if (ObjectUtils.isEmpty(xwlUser1)) {
            log.error("根据"+sp+"访问用户信息体查询失败->"+XwlResultErrorEnum.USER_NOT_NULL);
            return R.exceptionReturn(XwlResultErrorEnum.USER_NOT_NULL);
        }
        log.info("根据id访问用户信息体"+XwlResultErrorEnum.SUCCESS);
        return R.OK(xwlUser1);
    }
}
