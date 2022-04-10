package com.sea.springcloud.common.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import java.util.Map;
import java.util.Set;

/**
 * 资源服务器
 */
@Configuration
@EnableResourceServer
@RequiredArgsConstructor
@Import({NoAuthConfig.class})
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private final NoAuthConfig noAuthConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        for (Map.Entry<String, Set<String>> entry : noAuthConfig.getPaths().entrySet()) {
            for (String httpMethod : entry.getValue()) {
                http.authorizeRequests().antMatchers(HttpMethod.valueOf(httpMethod), entry.getKey()).permitAll();
            }
        }
        http.authorizeRequests()
                .anyRequest()
                .authenticated()  //任何请求都需要身份认证
                .and()
                .csrf()
                .disable();    //禁用CSRF
    }

}
