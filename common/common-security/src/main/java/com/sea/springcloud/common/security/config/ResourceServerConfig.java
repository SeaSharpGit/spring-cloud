package com.sea.springcloud.common.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器
 */
@Configuration
@EnableResourceServer
@RequiredArgsConstructor
@Import({NoAuthConfig.class})
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private final NoAuthConfig noAuthConfig;
//    private final RemoteTokenServices remoteTokenServices;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        noAuthConfig.permitAll(http);
        http.authorizeRequests()
                .anyRequest()
                .authenticated()  //任何请求都需要身份认证
                .and()
                .csrf()
                .disable();    //禁用CSRF
    }

//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
//        accessTokenConverter.setUserTokenConverter(new GuiyunUserAuthenticationConverter());
//
//        remoteTokenServices.setRestTemplate(lbRestTemplate);
//        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
//        resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
//                .tokenExtractor(tokenExtractor)
//                .tokenServices(remoteTokenServices);
//    }

}
