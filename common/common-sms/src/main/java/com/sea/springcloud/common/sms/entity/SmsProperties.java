package com.sea.springcloud.common.sms.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * #短信
 * sms:
 * enable: true
 * endpoint: dysmsapi.aliyuncs.com
 * sign-name: xxx
 * access-key-id: xxx
 * access-key-secret: xxx
 */
@Data
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {

    /**
     * 短信开关
     */
    private Boolean enable;

    /**
     * 对象存储服务的URL
     */
    private String endpoint;

    /**
     * 签名
     */
    private String signName;

    /**
     * 账号
     */
    private String accessKeyId;

    /**
     * 密码
     */
    private String accessKeySecret;

}
