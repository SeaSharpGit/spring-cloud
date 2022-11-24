package com.sea.springcloud.common.xxljob.config;

import com.sea.springcloud.common.xxljob.entity.XxlJobProperties;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(XxlJobProperties.class)
@RequiredArgsConstructor
public class XxlJobConfig {
    private final XxlJobProperties xxlJobProperties;
    private final Environment environment;
    private final static String XXL_JOB_ADMIN_SERVICE_NAME="xxl-job-admin";

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor(DiscoveryClient discoveryClient) {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAppname(environment.getProperty("spring.application.name"));
        xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getAccessToken());
        xxlJobSpringExecutor.setPort(xxlJobProperties.getExecutor().getPort());

        String adminAddresses = discoveryClient.getInstances(XXL_JOB_ADMIN_SERVICE_NAME).stream()
                .map(a -> String.format("http://%s:%s/%s", a.getHost(), a.getPort(), XXL_JOB_ADMIN_SERVICE_NAME))
                .collect(Collectors.joining(","));
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);

        return xxlJobSpringExecutor;
    }

}
