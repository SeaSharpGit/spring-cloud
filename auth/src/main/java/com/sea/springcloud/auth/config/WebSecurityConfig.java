package com.sea.springcloud.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin(Customizer.withDefaults());
    }

    /**
     * 密码编码器
     * 密码存储格式：{type}encodedPassword
     * {type}不会造成问题，因为攻击者无需前缀即可识别出来加密类型
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        //使用默认密码编码器
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}