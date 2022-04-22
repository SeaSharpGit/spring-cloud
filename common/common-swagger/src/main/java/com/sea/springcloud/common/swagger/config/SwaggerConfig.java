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
    private final String OAUTH2_HEADER_KEY = "Authorization";

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
                .securitySchemes(Collections.singletonList(securityScheme()))
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

//    private SecurityScheme securityScheme() {
//        return new ApiKey("Authorization", "OAuth2", "header");
//    }

    private SecurityScheme securityScheme() {
        List<AuthorizationScope> scopes = swaggerProperties.getAuthorization().getScopes().stream()
                .map(a -> new AuthorizationScope(a.getName(), a.getDescription()))
                .collect(Collectors.toList());
        return OAuth2Scheme.OAUTH2_PASSWORD_FLOW_BUILDER
                .name(OAUTH2_HEADER_KEY)
                .tokenUrl(swaggerProperties.getAuthorization().getTokenUrl())
//                .authorizationUrl("https://www.baidu.com")
                .scopes(scopes)
                .refreshUrl("")
                .build();
//        OAuth2Scheme authorization = OAuth2Scheme.OAUTH2_AUTHORIZATION_CODE_FLOW_BUILDER
//                .name("Authorization")
//                .tokenUrl(properties.getAccessTokenUri())
//                .authorizationUrl(properties.getAuthorizationUri())
//                .scopes(Arrays.asList(scopes()))
//                .refreshUrl(properties.getRefreshTokenUri())
//                .build();
//       OAuth2Scheme clientCredentials = OAuth2Scheme.OAUTH2_CLIENT_CREDENTIALS_FLOW_BUILDER
//                .name("Authorization")
//                .tokenUrl(properties.getAccessTokenUri())
//                .authorizationUrl(properties.getAuthorizationUri())
//                .scopes(Arrays.asList(scopes()))
//                .refreshUrl(properties.getRefreshTokenUri())
//                .build();
//        OAuth2Scheme password = OAuth2Scheme.OAUTH2_PASSWORD_FLOW_BUILDER
//                .name("Authorization")
//                .tokenUrl(properties.getAccessTokenUri())
//                .authorizationUrl(properties.getAuthorizationUri())
//                .scopes(Arrays.asList(scopes()))
//                .refreshUrl(properties.getRefreshTokenUri())
//                .build();
    }

    /**
     * 全局鉴权策略
     */
    private SecurityContext securityContext() {
        List<AuthorizationScope> authorizationScopes = swaggerProperties.getAuthorization().getScopes().stream()
                .map(a -> new AuthorizationScope(a.getName(), a.getDescription()))
                .collect(Collectors.toList());
        AuthorizationScope[] scopes = authorizationScopes.toArray(new AuthorizationScope[authorizationScopes.size() - 1]);
        List<SecurityReference> securityReferences = new ArrayList<SecurityReference>() {{
            add(new SecurityReference(OAUTH2_HEADER_KEY, scopes));
        }};
        return SecurityContext.builder().securityReferences(securityReferences).build();
    }

}
