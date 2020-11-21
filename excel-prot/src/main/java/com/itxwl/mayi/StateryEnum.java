package com.itxwl.mayi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateryEnum {

    ALI_YUN("com.itxwl.mayi.AliyunStatery"),
    WEI_XIN("com.itxwl.mayi.WeiXinStatery"),
    ALI_YUN_BEAN("aliyunStatery"),
    WEI_XIN_BEAN("weiXinStatery");


    String stateryName;

}
