package com.sea.springcloud.gateway.swagger;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class GatewaySwaggerConfig {
    private final SwaggerHandler swaggerHandler;
    private final SwaggerUiHandler swaggerUiHandler;
    private final SwaggerSecurityHandler swaggerSecurityHandler;

    @Bean
    public RouterFunction<ServerResponse> swaggerRouterFunction() {
        return RouterFunctions
                .route(RequestPredicates.GET("/swagger-resources").and(RequestPredicates.accept(MediaType.ALL)), swaggerHandler)
                .andRoute(RequestPredicates.GET("/swagger-resources/configuration/ui").and(RequestPredicates.accept(MediaType.ALL)), swaggerUiHandler)
                .andRoute(RequestPredicates.GET("/swagger-resources/configuration/security").and(RequestPredicates.accept(MediaType.ALL)), swaggerSecurityHandler);
    }

}
