package com.xwl.controller;

import com.xwl.annotation.RequestUser;
import com.xwl.config.enums.XwlResultErrorEnum;
import com.xwl.domain.XwlUser;
import com.xwl.mapper.XwlUserMapper;
import com.xwl.statery.appint.BusinessHandlerChoose;
import com.xwl.statery.config.BusinessStrategyConstant;
import com.xwl.statery.factory.BusinessProcessFactory;
import com.xwl.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueWenLiang
 * @date 2021/4/29 16:30
 * @Description 描述信息
 */
@RequestMapping("xwluser")
@RestController
@AllArgsConstructor
public class UserAnnotationController {
    private XwlUserMapper xwlUserMapper;

    private BusinessHandlerChoose businessHandlerChoose;
    @GetMapping("getUserInfo")
    public R<XwlUser> getUserInfo(@RequestUser XwlUser xwlUser) {
        System.out.println("获取用户信息" + xwlUser.toString());
        XwlUser xwlUser1 = xwlUserMapper.findUserByName(xwlUser.getUserName());
        if (ObjectUtils.isEmpty(xwlUser1)) {
            return R.exceptionReturn(XwlResultErrorEnum.USER_NOT_NULL);
        }
        return R.OK(xwlUser1);
    }

    /**
     * 测试通用获取执行查1和2
     * @param id
     * @return
     */
    @GetMapping("stateryTest/{id}")
    public R<XwlUser> stateryTest(@PathVariable ("id") Long id){
        String value = String.valueOf(id);
        BusinessProcessFactory businessProcessFactory = businessHandlerChoose.businessHandlerChooser(BusinessStrategyConstant.DemoOne.HAHA, value);
        XwlUser xwlUser = (XwlUser) businessProcessFactory.businessProcess(id);
        return R.OK(xwlUser);
    }
}
