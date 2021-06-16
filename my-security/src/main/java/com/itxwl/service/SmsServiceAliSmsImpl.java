package com.itxwl.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.itxwl.config.AppProperties;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * @author xueWenLiang
 * @date 2021/6/7 15:13
 * @Description 描述信息
 */
@Slf4j
@Service
@ConditionalOnProperty(prefix = "mooc.sms-provider",name = "name",havingValue = "ali")
public class SmsServiceAliSmsImpl implements SmsService {
    @Autowired
    private  IAcsClient client;
    @Autowired
    private  AppProperties appProperties;
    @Override
    public void send(String mobile, String msg) {
        val request=new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(appProperties.getSmsProvider().getApiUrl());
        request.setSysAction("SendSms");
        request.setSysVersion("2017-05-25");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", "登录验证");
        request.putQueryParameter("TemplateCode", "SMS_1610048");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" +
                msg +
                "\",\"product\":\"慕课网实战Spring Security\"}");
        try {
            val response = client.getCommonResponse(request);
            log.info("短信发送结果 {}", response.getData());
        } catch (ServerException e) {
            log.error("发送短信时产生服务端异常 {}", e.getLocalizedMessage());
        } catch (ClientException e) {
            log.error("发送短信时产生客户端异常 {}", e.getLocalizedMessage());
        }
    }
}
