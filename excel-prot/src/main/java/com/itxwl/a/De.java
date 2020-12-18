package com.itxwl.a;

import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 薛
 * @Date: 2020/12/4 16:28
 * @Description:
 */
public class De {
    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<>();
        boolean empty = MapUtils.isEmpty(map);
        System.out.println(empty);
        if (map.size()==0){
            System.out.println("空");
        }
    }
}
