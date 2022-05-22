package com.sea.springcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringCloud Gateway 基于 WebFlux 实现，WebFlux 基于 Netty 实现，替换了 Zuul
 * WebFlux：少量 Loop 线程处理 request 和 response，work 线程处理阻塞的业务操作
 * Zuul：基于 servlet，每个请求分配一个线程。
 * SpringCloud Zuul：基于 Zuul 的阻塞式处理模型，一个 servlet 处理所有请求
 * Zuul2：基于 Netty，不过 SpringCloud 没有集成Zuul2的计划
 */
@SpringBootApplication
public class Gateway {
    public static void main(String[] args) {
        SpringApplication.run(Gateway.class, args);
    }
}
