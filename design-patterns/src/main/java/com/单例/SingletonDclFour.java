package com.单例;

/**
 * @Auther: 薛
 * @Date: 2021/1/16 15:36
 * @Description:
 */

/**
 * 双检锁/双重校验锁（DCL，即 double-checked locking）
 * JDK 版本：JDK1.5 起
 * 是否 Lazy 初始化：是
 * 是否多线程安全：是
 * 描述：这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 * getInstance() 的性能对应用程序很关键。
 */
public class SingletonDclFour {
    private SingletonDclFour(){}
    private volatile  static  SingletonDclFour singletonDclFour;
    public static SingletonDclFour getInstance(){
        synchronized (SingletonDclFour.class){
            if (singletonDclFour==null){
                singletonDclFour=new SingletonDclFour();
            }
        }
        return singletonDclFour;
    }
}
