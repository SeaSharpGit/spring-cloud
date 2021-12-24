package com.sea.springcloud.common.core.annotation;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.annotation.*;

@EnableFeignClients(basePackages = "com.sea.springcloud")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface EnableAllFeignClients {

}
