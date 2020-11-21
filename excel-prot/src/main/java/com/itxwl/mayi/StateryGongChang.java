package com.itxwl.mayi;

/**
 * @Auther: 薛
 * @Date: 2020/11/21 17:17
 * @Description:
 */
// TODO 具体调用执行策略工厂分发类
public class StateryGongChang {
    /**
     * 没有使用自动注入
     *
     * @param pageCodeType
     * @return
     */
    public static MyStatery getStatery(String pageCodeType) {
        MyStatery myStatery = null;
        try {
            String stateryName = StateryEnum.valueOf(pageCodeType).getStateryName();
            myStatery = (MyStatery) Class.forName(stateryName).newInstance();
            return myStatery;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return myStatery;
    }

    /**
     * 使用自动注入
     *
     * @param pageCodeType
     * @return
     */
    public static MyStatery getStateryBean(String pageCodeType) throws RuntimeException {
        MyStatery myStatery = null;
        String stateryName = null;
        try {
            stateryName = StateryEnum.valueOf(pageCodeType).getStateryName();
        } catch (Exception e) {
           throw  new RuntimeException("暂无此策略模式");
        }
        myStatery = SpringUtils.getBean(stateryName, MyStatery.class);
        return myStatery;
    }
}
