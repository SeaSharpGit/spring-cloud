package com.sea.springcloud.common.swagger.config;

import com.sea.springcloud.common.swagger.entity.SwaggerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@EnableOpenApi
@ConditionalOnProperty(name = "swagger.enabled")
@EnableConfigurationProperties(SwaggerProperties.class)
@RequiredArgsConstructor
public class SwaggerConfig {
    private final SwaggerProperties swaggerProperties;

    @Bean
    public Docket createRestApi() {
        List<RequestParameter> headers = new ArrayList<>();
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .globalRequestParameters(headers)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sea.springcloud"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .version(swaggerProperties.getVersion())
                .contact(new Contact(
                        swaggerProperties.getContact().getName(),
                        swaggerProperties.getContact().getUrl(),
                        swaggerProperties.getContact().getEmail()
                )).build();
    }

    private OAuth securitySchema() {
        String name = swaggerProperties.getAuthorization().getName();
        List<AuthorizationScope> authorizationScopes = swaggerProperties.getAuthorization().getScopes().stream()
                .map(a -> new AuthorizationScope(a.getName(), a.getDescription()))
                .collect(Collectors.toList());
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(swaggerProperties.getAuthorization().getTokenUrl());
        return new OAuth(name, authorizationScopes, Collections.singletonList(grantType));
    }

    /**
     * 全局鉴权策略
     */
    private SecurityContext securityContext() {
        List<AuthorizationScope> authorizationScopes = swaggerProperties.getAuthorization().getScopes().stream()
                .map(a -> new AuthorizationScope(a.getName(), a.getDescription()))
                .collect(Collectors.toList());
        AuthorizationScope[] scopes = authorizationScopes.toArray(new AuthorizationScope[authorizationScopes.size() - 1]);
        SecurityReference securityReference = SecurityReference.builder()
                .reference(swaggerProperties.getAuthorization().getName())
                .scopes(scopes)
                .build();
        return SecurityContext.builder().securityReferences(Collections.singletonList(securityReference)).build();
    }

}
