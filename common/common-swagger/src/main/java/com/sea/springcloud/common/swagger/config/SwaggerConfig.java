package com.sea.springcloud.common.swagger.config;

import com.sea.springcloud.common.swagger.entity.SwaggerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Swagger3存在baseUrl在微服务下缺失，所以使用Swagger2
 */
@EnableOpenApi
@ConditionalOnProperty(name = "swagger.enabled")
@EnableConfigurationProperties(SwaggerProperties.class)
@RequiredArgsConstructor
public class SwaggerConfig {
    private final SwaggerProperties swaggerProperties;
    private final String OAUTH2_HEADER_KEY = "Authorization";

    @Bean
    public Docket createRestApi() {
        //灰度
        List<RequestParameter> headers = new ArrayList<RequestParameter>() {{
            add(new RequestParameterBuilder().description("灰度")
                    .in(ParameterType.HEADER)
                    .name("version")
                    .required(false)
                    .query(a -> a.model(b -> b.scalarModel(ScalarType.STRING)))
                    .build());
        }};
        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerProperties.getHost())
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
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .license(swaggerProperties.getLicense())
                .licenseUrl(swaggerProperties.getLicenseUrl())
                .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                .contact(new Contact(
                        swaggerProperties.getContact().getName(),
                        swaggerProperties.getContact().getUrl(),
                        swaggerProperties.getContact().getEmail()
                )).build();
    }

    private SecurityScheme securityScheme() {
        List<GrantType> grantTypes = Collections.singletonList(new ResourceOwnerPasswordCredentialsGrant(swaggerProperties.getAuthorization().getTokenUrl()));
        return new OAuth(OAUTH2_HEADER_KEY, swaggerProperties.getScopes(), grantTypes);
    }

    //因为swagger在baseUrl上存在BUG，所以改用swagger2
//    private SecurityScheme securityScheme() {
//        return OAuth2Scheme.OAUTH2_PASSWORD_FLOW_BUILDER
//                .name(OAUTH2_HEADER_KEY)
//                .tokenUrl(swaggerProperties.getAuthorization().getTokenUrl())
//                .scopes(swaggerProperties.getScopes())
////                .refreshUrl("")
//                .build();
////        OAuth2Scheme authorization = OAuth2Scheme.OAUTH2_AUTHORIZATION_CODE_FLOW_BUILDER
////                .name("Authorization")
////                .tokenUrl(properties.getAccessTokenUri())
////                .authorizationUrl(properties.getAuthorizationUri())
////                .scopes(Arrays.asList(scopes()))
////                .refreshUrl(properties.getRefreshTokenUri())
////                .build();
////       OAuth2Scheme clientCredentials = OAuth2Scheme.OAUTH2_CLIENT_CREDENTIALS_FLOW_BUILDER
////                .name("Authorization")
////                .tokenUrl(properties.getAccessTokenUri())
////                .authorizationUrl(properties.getAuthorizationUri())
////                .scopes(Arrays.asList(scopes()))
////                .refreshUrl(properties.getRefreshTokenUri())
////                .build();
////        OAuth2Scheme password = OAuth2Scheme.OAUTH2_PASSWORD_FLOW_BUILDER
////                .name("Authorization")
////                .tokenUrl(properties.getAccessTokenUri())
////                .authorizationUrl(properties.getAuthorizationUri())
////                .scopes(Arrays.asList(scopes()))
////                .refreshUrl(properties.getRefreshTokenUri())
////                .build();
//    }

    /**
     * 全局鉴权策略
     */
    private SecurityContext securityContext() {
        List<AuthorizationScope> scopes = swaggerProperties.getScopes();
        AuthorizationScope[] scopeArray = new AuthorizationScope[scopes.size()];
        scopes.toArray(scopeArray);
        List<SecurityReference> securityReferences = new ArrayList<SecurityReference>() {{
            add(new SecurityReference(OAUTH2_HEADER_KEY, scopeArray));
        }};
        return SecurityContext.builder().securityReferences(securityReferences).build();
    }

}
