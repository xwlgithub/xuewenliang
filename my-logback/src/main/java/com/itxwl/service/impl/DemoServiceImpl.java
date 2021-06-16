package com.itxwl.service.impl;

import com.itxwl.domain.XwlUser;
import com.itxwl.mapper.XwlUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author xueWenLiang
 * @date 2021/6/4 16:40
 * @Description 描述信息
 */
@Service
@RequiredArgsConstructor
public class DemoServiceImpl  implements DemoService{
    private final XwlUserMapper xwlUserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ddd(XwlUser xwlUser) {
        saves(xwlUser);
    }
    public boolean saves(XwlUser xwlUser) {

            xwlUser.setUserName("fsafsafaf");
            xwlUser.setId(4646456l);
            xwlUserMapper.insert(xwlUser);
            int x=1/0;
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
        return true;
    }

}
