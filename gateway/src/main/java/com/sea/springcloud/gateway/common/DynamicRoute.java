package com.sea.springcloud.gateway.common;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

@Component
@Slf4j
@RequiredArgsConstructor
public class DynamicRoute implements ApplicationEventPublisherAware {
    private static final String dataId = "gateway-router";
    private static final String group = "DEFAULT_GROUP";
    private static final List<String> ROUTE_LIST = new ArrayList<>();
    private final NacosDiscoveryProperties nacosDiscoveryProperties;
    private final RouteDefinitionWriter routeDefinitionWriter;
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void dynamicRouteByNacosListener() {
        try {
            ConfigService configService = NacosFactory.createConfigService(nacosDiscoveryProperties.getServerAddr());
            initDynamicRoute(configService);
            addDynamicRouteListener(configService);
        } catch (NacosException e) {
            log.error("dynamicRouteByNacosListener error：" + e.getLocalizedMessage());
        }
    }

    private void initDynamicRoute(ConfigService configService) throws NacosException {
        String configInfo = configService.getConfig(dataId, group, 5000);
        JSON.parseObject(configInfo, new TypeReference<List<RouteDefinition>>() {
        }).forEach(this::addRoute);
        publish();
    }

    private void addDynamicRouteListener(ConfigService configService) throws NacosException {
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                clearRoute();
                if (StringUtil.isNullOrEmpty(configInfo)) {
                    return;
                }
                try {
                    JSON.parseObject(configInfo, new TypeReference<List<RouteDefinition>>() {
                    }).forEach(a -> addRoute(a));
                    publish();
                } catch (Exception e) {
                    log.error("receiveConfigInfo error：" + e.getLocalizedMessage());
                }
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    }

    private void clearRoute() {
        for (String id : ROUTE_LIST) {
            this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
        }
        ROUTE_LIST.clear();
    }

    private void addRoute(RouteDefinition definition) {
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            ROUTE_LIST.add(definition.getId());
        } catch (Exception e) {
            log.error("addRoute error：" + e.getLocalizedMessage());
        }
    }

    private void publish() {
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
    }

    @Override
    public void setApplicationEventPublisher(@Nonnull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

}
