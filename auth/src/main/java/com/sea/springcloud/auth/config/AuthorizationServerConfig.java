package com.sea.springcloud.auth.config;

import com.sea.springcloud.auth.service.OAuthClientDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final OAuthClientDetailsService oAuthClientDetailsService;

    @Override
    @SneakyThrows
    public void configure(AuthorizationServerSecurityConfigurer security) {
//        security.allowFormAuthenticationForClients();
    }

    @Override
    @SneakyThrows
    public void configure(ClientDetailsServiceConfigurer clients) {
        clients.withClientDetails(oAuthClientDetailsService);
    }

    @Override
    @SneakyThrows
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)  {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
//                .tokenStore(tokenStore());
    }

//    @Bean
//    public TokenStore tokenStore() {
//        return new InMemoryTokenStore();
//    }

}
