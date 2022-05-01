package com.sea.springcloud.common.swagger.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import springfox.documentation.service.AuthorizationScope;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private Boolean enabled = false;

    /**
     * 标题
     */
    private String title = "";

    /**
     * 描述
     */
    private String description = "";

    /**
     * 版本号
     */
    private String version = "";

    /**
     * 许可证
     */
    private String license = "";

    /**
     * 许可证URL
     */
    private String licenseUrl = "";

    /**
     * 服务条款URL
     */
    private String termsOfServiceUrl = "";

    /**
     * host
     */
    private String host = "";

    /**
     * 联系人
     */
    private Contact contact = new Contact();

    /**
     * 鉴权
     */
    private Authorization authorization = new Authorization();

    public List<AuthorizationScope> getScopes() {
        if (authorization == null) {
            return new ArrayList<>();
        }
        return authorization.getScopes().stream()
                .map(a -> new AuthorizationScope(a.getName(), a.getDescription()))
                .collect(Collectors.toList());
    }

    @Data
    public static class Contact {
        /**
         * 姓名
         */
        private String name = "";

        /**
         * 主页
         */
        private String url = "";

        /**
         * Email
         */
        private String email = "";
    }

    @Data
    public static class Authorization {
        /**
         * 作用域集合
         */
        private List<Scope> scopes = new ArrayList<>();

        /**
         * 获取token的地址
         */
        private String tokenUrl = "";
    }

    @Data
    public static class Scope {
        /**
         * 作用域标识
         */
        private String name = "";

        /**
         * 作用域描述
         */
        private String description = "";
    }
}
