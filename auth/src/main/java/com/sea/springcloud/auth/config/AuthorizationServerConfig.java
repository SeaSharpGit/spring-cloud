package com.sea.springcloud.auth.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final ClientDetailsService clientDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @SneakyThrows
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();
    }

    @Override
    @SneakyThrows
    public void configure(ClientDetailsServiceConfigurer clients) {
//        clients.withClientDetails(clientDetailsService);
        clients.inMemory()
                .withClient("admin")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("password")
                .scopes("all");
    }

    @Override
    @SneakyThrows
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)  {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

}
