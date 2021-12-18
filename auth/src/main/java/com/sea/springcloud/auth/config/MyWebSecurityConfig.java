package com.sea.springcloud.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {



    /**
     * 密码编码器
     * 密码存储格式：{type}encodedPassword
     * {type}不会造成问题，因为攻击者无需前缀即可识别出来加密类型
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        //使用默认密码编码器
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //使用自定义密码编码器
//        return new DelegatingPasswordEncoder("bcrypt",
//                new HashMap<String,PasswordEncoder>(){{
//                    put("noop", NoOpPasswordEncoder.getInstance());
//                            put("pbkdf2", new Pbkdf2PasswordEncoder());
//                            put("scrypt", new SCryptPasswordEncoder());
//                            put("sha256", new StandardPasswordEncoder());
//                }});
    }

}
