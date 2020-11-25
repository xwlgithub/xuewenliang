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
 * @Date: 2020/11/21 13:10
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwoDetail {
    @ExcelProperty(value = "姓名")
    private String name;
    @ExcelProperty(value = "地址")
    private String address;
    @ExcelProperty(value = "年龄")
    private String ages;
    @ExcelProperty(value = "邮箱")
    private String email;

    public static List<TwoDetail> getPlanDetail(Map<String,Object> params){
        List<TwoDetail> planDetails=new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            planDetails.add(new TwoDetail("李四","zhangsan","654","7569052"));
        }
        planDetails.add(new TwoDetail("李四四","ll","89","9+78"));
        if (params!=null){
            Iterator<TwoDetail> iterator = planDetails.iterator();
            while (iterator.hasNext()){
                TwoDetail next = iterator.next();
                String name = (String) params.get("name");
                if (next.getName().equals(name)){
                    iterator.remove();
                }
            }

        }
        return planDetails;
    }
}
