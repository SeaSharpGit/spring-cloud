package com.sea.springcloud.common.security.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * 资源服务器
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)//验证方法权限
@RequiredArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private final NoAuthConfig noAuthConfig;
    private final ResourceServerTokenServices resourceServerTokenServices;

    @SneakyThrows
    @Override
    public void configure(HttpSecurity http) {
        noAuthConfig.permitAll(http);
        http.authorizeRequests()
                .anyRequest()
                .authenticated()  //任何请求都需要身份认证
                .and()
                .csrf().disable();    //禁用CSRF
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenServices(resourceServerTokenServices);
    }
}
