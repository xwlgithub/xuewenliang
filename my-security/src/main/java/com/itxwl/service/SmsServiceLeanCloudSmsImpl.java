package com.itxwl.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xueWenLiang
 * @date 2021/6/7 16:07
 * @Description 描述信息
 */
@Slf4j
@Service
public class SmsServiceLeanCloudSmsImpl{
//        implements SmsService {
//    @Override
//    public void send(String mobile, String msg) {
//        val option = new AVSMSOption();
//        option.setTtl(10);
//        option.setApplicationName("慕课网实战Spring Security");
//        option.setOperation("两步验证");
//        option.setTemplateName("登录验证");
//        option.setSignatureName("慕课网");
//        option.setType(AVSMS.TYPE.TEXT_SMS);
//        Map  map=new HashMap<String,Object>();
//        map.put("smsCode", msg);
//        option.setEnvMap(map);
//        AVSMS.requestSMSCodeInBackground(mobile, option)
//                .take(1)
//                .subscribe(
//                        (res) -> log.info("短信发送成功 {}", res),
//                        (err) -> log.error("发送短信时产生服务端异常 {}", err.getLocalizedMessage())
//                );
//    }
}
