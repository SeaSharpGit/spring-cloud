package com.sea.springcloud.common.swagger.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * swagger配置
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    /**
     * 开关
     */
    private Boolean enabled;

    /**
     * 标题
     */
    private String title;

    /**
     * 备注
     */
    private String description;

    /**
     * 版本号
     */
    private String version;

    /**
     * 联系人
     */
    private Contact contact;

    @Data
    public static class Contact {
        /**
         * 姓名
         */
        private String name;

        /**
         * 主页
         */
        private String url;

        /**
         * Email
         */
        private String email;
    }
}
