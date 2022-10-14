package com.sea.springcloud.common.xxljob.config;

import com.sea.springcloud.common.xxljob.entity.XxlJobProperties;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import com.xxl.job.core.util.IpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@EnableConfigurationProperties(XxlJobProperties.class)
@RequiredArgsConstructor
public class XxlJobConfig {
    private final XxlJobProperties xxlJobProperties;
    private final Environment environment;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAppname(environment.getProperty("spring.application.name"));
        xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getAccessToken());
        xxlJobSpringExecutor.setAdminAddresses(xxlJobProperties.getAdmin().getAddresses());
        xxlJobSpringExecutor.setIp(IpUtil.getIp());
        xxlJobSpringExecutor.setPort(xxlJobProperties.getExecutor().getPort());
        return xxlJobSpringExecutor;
    }

}
