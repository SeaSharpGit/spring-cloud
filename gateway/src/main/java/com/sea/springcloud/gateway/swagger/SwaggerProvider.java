package com.sea.springcloud.gateway.swagger;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取注册中心中的资源 /swagger-resources
 */
@Component
@Primary
@RequiredArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {
    private final RouteDefinitionRepository routeDefinitionRepository;
    private final DiscoveryClient discoveryClient;

    @Override
    public List<SwaggerResource> get() {
        List<RouteDefinition> routeDefinitions = new ArrayList<>();
        routeDefinitionRepository.getRouteDefinitions().sort(Comparator.comparing(RouteDefinition::getOrder)).subscribe(routeDefinitions::add);
        return routeDefinitions.stream()
                .flatMap(a -> a.getPredicates().stream().map(b -> swaggerResource(a.getId(), b.getArgs().get("pattern").replace("/**", "/v2/api-docs"))))
                // 只显示已启动的服务
                .filter(a -> discoveryClient.getServices().stream().anyMatch(b -> b.equals(a.getName())))
                .collect(Collectors.toList());
    }

    private static SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

}
