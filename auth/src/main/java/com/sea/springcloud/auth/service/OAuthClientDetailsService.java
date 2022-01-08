package com.sea.springcloud.auth.service;

import com.sea.springcloud.auth.entity.OAuthClientDetails;
import com.sea.springcloud.user.entity.SysClient;
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
        SysClient sysClient = feignSysClientService.loadClientByClientId(clientId).getData();
        OAuthClientDetails result = new OAuthClientDetails();
        result.setClientId(sysClient.getClient());
        result.setClientSecret(sysClient.getSecret());
        for (String grantType : sysClient.getGrantType().split(",")) {
            result.getAuthorizedGrantTypes().add(grantType);
        }
        for (String scope : sysClient.getScope().split(",")) {
            result.getAuthorizedGrantTypes().add(scope);
        }
        result.setAccessTokenValiditySeconds(sysClient.getAccessExpire());
        result.setRefreshTokenValiditySeconds(sysClient.getRefreshExpire());
        result.setClientSecret(sysClient.getSecret());
        return result;
    }
}
