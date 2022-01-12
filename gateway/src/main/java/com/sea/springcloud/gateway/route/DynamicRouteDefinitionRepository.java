package com.sea.springcloud.gateway.route;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * 动态路由
 * 替换 spring cloud gateway 默认实现：{@link InMemoryRouteDefinitionRepository}
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class DynamicRouteDefinitionRepository implements RouteDefinitionRepository, ApplicationEventPublisherAware {
    private static final String dataId = "gateway-router";
    private static final String group = "DEFAULT_GROUP";
    private static final Map<String, RouteDefinition> routes = new HashMap<>();

    private final NacosConfigProperties nacosConfigProperties;
    private final ObjectMapper objectMapper;

    @Setter
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void dynamicRouteByNacosListener() {
        try {
            ConfigService configService = NacosFactory.createConfigService(nacosConfigProperties.getServerAddr());
            initDynamicRoute(configService);
            addDynamicRouteListener(configService);
        } catch (Exception e) {
            log.error("dynamicRouteByNacosListener error：" + e.getLocalizedMessage());
        }
    }

    private void initDynamicRoute(ConfigService configService) throws NacosException, JsonProcessingException {
        String configInfo = configService.getConfig(dataId, group, 5000);
        List<RouteDefinition> routeDefinitions = objectMapper.readValue(configInfo, new TypeReference<List<RouteDefinition>>() {
        });
        for (RouteDefinition item : routeDefinitions) {
            addRoute(item);
        }
        refresh();
    }

    private void addDynamicRouteListener(ConfigService configService) throws NacosException {
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                routes.clear();
                if (StringUtil.isNullOrEmpty(configInfo)) {
                    return;
                }
                try {
                    List<RouteDefinition> routeDefinitions = objectMapper.readValue(configInfo, new TypeReference<List<RouteDefinition>>() {
                    });
                    for (RouteDefinition item : routeDefinitions) {
                        addRoute(item);
                    }
                    refresh();
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

    private void addRoute(RouteDefinition definition) {
        routes.put(definition.getId(), definition);
    }

    private void refresh() {
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(routes.values());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return Mono.defer(() -> Mono.error(new NotFoundException("Unsupported operation")));
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return Mono.defer(() -> Mono.error(new NotFoundException("Unsupported operation")));
    }

}
