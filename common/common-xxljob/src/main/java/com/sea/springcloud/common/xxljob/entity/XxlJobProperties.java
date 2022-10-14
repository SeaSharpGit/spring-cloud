package com.sea.springcloud.common.xxljob.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 短信配置
 */
@Data
@ConfigurationProperties(prefix = "xxl.job")
@RefreshScope
public class XxlJobProperties {
    private String accessToken;

    private XxlJobAdminProperties admin = new XxlJobAdminProperties();

    @Data
    public static class XxlJobAdminProperties {
        /**
         * xxl-job-admin地址
         */
        private String addresses;
    }
}
