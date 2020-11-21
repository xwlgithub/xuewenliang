package com.itxwl.controller;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

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

    public static List<TwoDetail> getPlanDetail(){
        List<TwoDetail> planDetails=new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            planDetails.add(new TwoDetail("李四","zhangsan","654","7569052"));
        }
        return planDetails;
    }
}
