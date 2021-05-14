package com.itxwl.controller;

import com.itxwl.annotation.XwlLog;
import com.itxwl.config.enums.ApiReturnMsgEnmu;
import com.itxwl.config.exception.ApiErrorException;
import com.itxwl.domain.XwlUser;
import com.itxwl.mapper.XwlUserMapper;
import com.itxwl.utils.MyUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
/**
 * @author xueWenLiang
 * @date 2021/5/10 14:57
 * @Description 描述信息
 */
@RestController
@RequestMapping("my")
@AllArgsConstructor
public class LogBackController {
    private XwlUserMapper xwlUserMapper;

    private final Logger logger = LoggerFactory.getLogger(LogBackController.class);

    @GetMapping("testError")
    @XwlLog(describution = "测试")
    public Object testError(@RequestParam(value = "number", required = true) Integer number) {
        logger.info("[进入测试接口]:{}", "参数值" + number);
        int x = 0;
        try {
            x = 5 / number;
        } catch (Exception e) {
            logger.error("[测试失败->错误信息]:{}", MyUtils.getStackTrace(e));
            throw new ApiErrorException(ApiReturnMsgEnmu.THROW_ERROR_EXCEPTION);
        }
        return (Object) x;
    }

    @GetMapping("gaga")
    @XwlLog(describution = "计算器")
    public Object gaga(@RequestParam(value = "size", required = true) Integer size) {
        logger.info("[进入计算器接口]:{}", "参数值" + size);
        int x = 0;
        try {
            x = 5 / size;
        } catch (Exception e) {
            logger.error("[计算器执行失败->错误信息]:{}", MyUtils.getStackTrace(e));
            throw new ApiErrorException(ApiReturnMsgEnmu.LEVEL_ERROR);
        }
        return (Object) x;
    }

    @PostMapping("saveUser")
    @XwlLog(describution = "保存用户")
    public Boolean gaga(XwlUser xwlUser) {
        logger.info("[开始保存用户]:{}", "参数值" + xwlUser.toString());
        if (ObjectUtils.isEmpty(xwlUser)) {
            logger.info("用户信息为空");
        }
        int x = 0;
        try {
            x = xwlUserMapper.insert(xwlUser);
        } catch (Exception e) {
            logger.error("[计算器执行失败->错误信息]:{}", MyUtils.getStackTrace(e));
            throw new ApiErrorException(ApiReturnMsgEnmu.SAVE_ERROR);
        }
        return x > 0 ? true : false;
    }


}
