package com.sea.springcloud.common.sms.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 短信
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
