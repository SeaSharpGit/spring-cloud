package com.sea.springcloud.auth.service;

import com.sea.springcloud.user.feign.FeignSysClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthClientDetailsService implements ClientDetailsService {
    private final FeignSysClientService feignSysClientService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return feignSysClientService.loadClientByClientId(clientId).getData();
    }
}
