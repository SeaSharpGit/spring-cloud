package com.sea.springcloud.user.sms;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
//@EnableConfigurationProperties(SmsProperties.class)
public class SmsUtils {


   // private final SmsProperties smsProperties;

//    public void sendMsg(String phone, String aLiYunSmsTemplateCode, String... parameters) throws Exception {
//        Map<String, String> map = new HashMap<>();
//        if (parameters.length % 2 != 0) {
//            throw new RuntimeException("参数parameters的数量只能为偶数");
//        }
//        for (int i = 0; i < parameters.length; i = i + 2) {
//            map.put(parameters[i], parameters[i + 1]);
//        }
//        sendMsg(phone, aLiYunSmsTemplateCode, map);
//    }

//    private void sendMsg(String phone, String aLiYunSmsTemplateCode, Map<String, String> map) throws Exception {
//        JSON.
//        String params = JSONUtil.toJsonStr(map);
//        sendMsg(phone, aLiYunSmsTemplateCode, JSON.);
//    }
//
//    private void sendMsg(String phone, String aLiYunSmsTemplateCode, String params) throws Exception {
//        if (!checkConfiguration()) {
//            return;
//        }
//        Config config = new Config()
//                // 您的AccessKey ID
//                .setAccessKeyId(smsProperties.getAccessKeyId())
//                // 您的AccessKey Secret
//                .setAccessKeySecret(smsProperties.getAccessKeySecret());
//        // 访问的域名
//        config.endpoint = smsProperties.getEndpoint();
//        com.aliyun.dysmsapi20170525.Client client = new com.aliyun.dysmsapi20170525.Client(config);
//
//        SendSmsRequest sendSmsRequest = new SendSmsRequest()
//                .setPhoneNumbers(phone)
//                .setSignName(smsProperties.getSignName())
//                .setTemplateCode(aLiYunSmsTemplateCode)
//                .setTemplateParam(params);
//        SendSmsResponse response = client.sendSms(sendSmsRequest);
//        if (response.getBody().getCode().equals("OK")) {
//
//        } else {
//            throw new RuntimeException("发送短信失败：" + response.getBody().getMessage());
//        }
//    }
//
//    private boolean checkConfiguration() {
//        if (smsProperties == null) {
//            throw new RuntimeException("短信服务缺少配置项：sms");
//        }
//        return smsProperties.getEnable();
//    }

}
