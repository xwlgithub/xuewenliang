package com.itxwl.controller;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: 薛
 * @Date: 2020/11/20 14:05
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDetail {
    @ExcelProperty(value = "姓名")
    private String name;
    @ExcelProperty(value = "地址")
    private String address;
    @ExcelProperty(value = "年龄")
    private String ages;
    @ExcelProperty(value = "邮箱")
    private String email;

    public  static List<PlanDetail> getPlanDetail(Map<String,Object> maps){
        List<PlanDetail> planDetails=new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            planDetails.add(new PlanDetail("张三","lisi","456","2509647"));
        }
        planDetails.add(new PlanDetail("张三三","ll","89","9+78"));
        if (maps!=null){
            Iterator<PlanDetail> iterator = planDetails.iterator();
            while (iterator.hasNext()){
                PlanDetail next = iterator.next();
                if (next.getName().equals((String) maps.get("name")))iterator.remove();
            }

        }
        return planDetails;
    }
}
