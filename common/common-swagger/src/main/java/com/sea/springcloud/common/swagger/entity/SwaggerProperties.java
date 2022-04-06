package com.sea.springcloud.common.swagger.entity;

import io.swagger.models.Contact;
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
    private Boolean enabled;
    private String basePackage;
    private String host;
    private String title;
    private String description;
    private String version;
    private String license;
    private String licenseUrl;
    private String termsOfServiceUrl;
    private Contact contact;

}
