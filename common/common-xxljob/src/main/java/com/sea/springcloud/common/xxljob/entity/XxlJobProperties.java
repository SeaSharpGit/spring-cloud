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

    private Executor executor = new Executor();

    @Data
    public static class Executor {
        /**
         * 执行器端口
         */
        private Integer port;
    }

}
