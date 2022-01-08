package com.sea.springcloud.auth;

import com.sea.springcloud.common.core.annotation.EnableAllFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAllFeignClients
public class Auth {
    public static void main(String[] args) {
        SpringApplication.run(Auth.class, args);
    }

}
