package com.单例;

import org.junit.Test;

/**
 * @Auther: 薛
 * @Date: 2021/1/16 14:44
 * @Description:
 */
public class SingletonTest {
    @Test
    public void SingletonTest(){
        SingletonThree instance = SingletonThree.getInstance();
        instance.showMessage();
    }
}
