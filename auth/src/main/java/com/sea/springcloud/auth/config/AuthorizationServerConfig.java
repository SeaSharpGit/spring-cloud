package com.sea.springcloud.auth.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final ClientDetailsService clientDetailsService;
    private final RedisConnectionFactory redisConnectionFactory;
    private final TokenEnhancer tokenEnhancer;

    @Override
    @SneakyThrows
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    @SneakyThrows
    public void configure(ClientDetailsServiceConfigurer clients) {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    @SneakyThrows
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST).tokenStore(redisTokenStore)
//                .tokenEnhancer(tokenEnhancer).userDetailsService(guiyunUserDetailsService)
//                .authorizationCodeServices(authorizationCodeServices).authenticationManager(authenticationManagerBean)
//                .reuseRefreshTokens(false).pathMapping("/oauth/confirm_access", "/token/confirm_access")
//                .exceptionTranslator(new GuiyunWebResponseExceptionTranslator());


        endpoints.authenticationManager(authenticationManager)
                .tokenStore(redisTokenStore())
                .tokenEnhancer(tokenEnhancer)
                .userDetailsService(userDetailsService);
    }

    @Bean
    public RedisTokenStore redisTokenStore() {
        //        redisTokenStore.setPrefix("oauth2");
        return new RedisTokenStore(redisConnectionFactory);
    }

}
