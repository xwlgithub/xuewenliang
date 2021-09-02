package com.xwl.statery.impl;

import com.xwl.domain.XwlUser;
import com.xwl.mapper.XwlUserMapper;
import com.xwl.statery.annotation.BusinessProcessAnnotation;
import com.xwl.statery.config.BusinessStrategyConstant;
import com.xwl.statery.factory.BusinessProcessFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author xueWenLiang
 * @date 2021/9/2 15:09
 * @Description 描述信息
 */
@Service
@RequiredArgsConstructor
@BusinessProcessAnnotation(type = BusinessStrategyConstant.DemoOne.HAHA, source = BusinessStrategyConstant.DemoOne.ONE)
public class DemoOneImpl implements BusinessProcessFactory<XwlUser, Long> {
    private final XwlUserMapper xwlUserMapper;


    @Override
    public XwlUser businessProcess(Long id) {
        XwlUser xwlUser = xwlUserMapper.selectById(id);
        return xwlUser;
    }
}
