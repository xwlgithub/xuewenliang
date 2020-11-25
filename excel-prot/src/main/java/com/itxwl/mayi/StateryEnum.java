package com.itxwl.mayi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateryEnum {

//    ALI_YUN("com.itxwl.mayi.AliyunStatery","com.itxwl.controller.PlanDetail"),
//    WEI_XIN("com.itxwl.mayi.WeiXinStatery","com.itxwl.controller.PlanDetail"),
    ALI_YUN_BEAN("aliyunStatery","com.itxwl.controller.PlanDetail"),
    WEI_XIN_BEAN("weiXinStatery","com.itxwl.controller.TwoDetail");


    String stateryName;
    String domainClassPath;

}
