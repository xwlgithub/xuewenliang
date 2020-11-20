package com.itxwl.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/11/20 14:05
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDetail {

    private String name;
    private String address;
    private String ages;
    private String email;

    public static List<PlanDetail> getPlanDetail(){
        List<PlanDetail> planDetails=new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            planDetails.add(new PlanDetail("张三","lisi","456","2509647"));
        }
        return planDetails;
    }
}
