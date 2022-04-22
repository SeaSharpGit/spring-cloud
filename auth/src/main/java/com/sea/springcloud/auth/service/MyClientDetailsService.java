package com.sea.springcloud.auth.service;

import com.sea.springcloud.auth.entity.MyClientDetails;
import com.sea.springcloud.user.entity.SysClient;
import com.sea.springcloud.user.feign.FeignSysClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * 替换默认实现：{@link InMemoryClientDetailsService}
 */
@Service
@RequiredArgsConstructor
@Primary
public class MyClientDetailsService implements ClientDetailsService {
    private final FeignSysClientService feignSysClientService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        SysClient sysClient = feignSysClientService.loadClientByClientId(clientId).getData();
        MyClientDetails result = new MyClientDetails();
        result.setClientId(sysClient.getClientId());
        result.setClientSecret(sysClient.getClientSecret());
        for (String grantType : sysClient.getAuthorizedGrantTypes().split(",")) {
            result.getAuthorizedGrantTypes().add(grantType);
        }
        for (String scope : sysClient.getScope().split(",")) {
            result.getScope().add(scope);
        }
        result.setAccessTokenValiditySeconds(sysClient.getAccessTokenValiditySeconds());
        result.setRefreshTokenValiditySeconds(sysClient.getRefreshTokenValiditySeconds());
        return result;
    }
}
